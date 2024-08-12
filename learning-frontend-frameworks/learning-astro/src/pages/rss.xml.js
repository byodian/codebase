import rss, { pageGlobToRssItems } from '@astrojs/rss';

export async function GET(context) {
  return rss({
    title: 'Astro Learner | Blog',
    description: 'My journery learning Astro',
    site: context.site,
    items: await pageGlobToRssItems(import.meta.glob('./**/*.md')),
    customData: `<language>en-us</language>`
  })
}