import Route from '@ember/routing/route';

let bookmarks = [
  {
    id: 1,
    title: 'Microsoft',
    link: 'http://microsoft.com',
    about: 'Micro micro'
  },
  {
    id: 2,
    title: 'Google',
    link: 'http://google.com',
    about: 'Search'
  },
  {
    id: 3,
    title: 'Wikipedia',
    link: 'http://wikipedia.org',
    about: 'wikipedia!'
  }
]
export default class AboutRoute extends Route {
  model() {
    return bookmarks
  }
}
