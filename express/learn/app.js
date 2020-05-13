var http = require('http')
var express = require('express')
var app = express()

http.createServer(app).listen(3000, function () {
  console.log('Express app started')
})

app.set('view engine', 'pug')
app.set('views', './views')

app.get('/', function (req, res) {
  res.render('index')
})

app.get('/hello', function (req, res) {
  res.render('hello')
})

app.use(express.static('./public'))
