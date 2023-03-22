import asyncio
import json
from playwright.async_api import async_playwright

jieqi = ["立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑",
         "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至", "小寒", "大寒"]

sem = asyncio.Semaphore(5)


async def get_one(year: int, sem):
    async with sem:
        async with async_playwright() as p:
            date = []
            fin = []
            browser = await p.chromium.launch(channel='msedge', args=['--user-agent="Mobile"'])
            page = await browser.new_page()
            await page.wait_for_timeout(500)
            await page.goto(f'https://jieqi.911cha.com/{year}.html')
            await page.wait_for_timeout(2000)
            print(year)
            results = await page.query_selector_all('#top > div:nth-child(5) > ul > li > a > span.appintro')
            if len(results) == 0:
                raise Exception("No data.")
            else:
                for result in results:
                    date.append(await result.text_content())
                fin = dict(zip(jieqi, date))
                await page.wait_for_timeout(500)
                await browser.close()
                with open(f'./data/{year}.json', 'w', encoding='utf-8') as f:
                    json.dump(fin, f, ensure_ascii=False)


async def main(sem):
    tasks = [asyncio.create_task(get_one(year, sem))
             for year in range(2050, 2101)]
    await asyncio.gather(*tasks)


if __name__ == '__main__':
    asyncio.run(main(sem))
