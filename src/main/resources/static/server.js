var express = require('express');
var path    = require('path');
var app     = express();
var rootDir = path.resolve(__dirname);

app.set('port', (process.env.PORT || 5000));

app.use(express.static(rootDir));

// views is directory for all template files
//app.set('views', __dirname + '/views');
//app.set('view engine', 'ejs');

app.all('/*', function(req, res) {
    res.sendFile(path.resolve(rootDir, 'index.html'));
});

app.listen(app.get('port'), function() {
    console.log('Node app is running on port', app.get('port'));
});