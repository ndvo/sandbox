import { helper } from '@ember/component/helper';

export default helper(function bookmarkTitle([title, about]/*, hash*/) {
  let result = title
  if (about != null)
    result += ' - ' + about
  return result;
});
