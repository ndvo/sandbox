
document.querySelectorAll('#use-all input').forEach(
  e => e.addEventListener('change', findMaximumPossibleClusters)
);

function findMaximumPossibleClusters(event){
  var x = Number.parseInt(document.querySelector('input').value);
  var y = Number.parseInt(document.querySelector('form div+div input').value);
  result = 1;
  for (var i = Math.min(x, y) ; i > 0; i--){
    if (x%i == y%i){
      result = i;
      break;
    }
  }
  document.querySelector('output').innerText = result;
}
