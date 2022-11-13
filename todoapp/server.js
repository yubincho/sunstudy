// 서버 띄우기 위한 기본 세팅

const express = require('express');
const app = express();

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true })) 


const MongoClient = require('mongodb').MongoClient;
app.set('view engine', 'ejs');

app.engine('ejs', require('ejs').__express)

var db; // DB 연결에 필요한 변수 설정

MongoClient.connect('mongodb+srv://admin:admin@cluster0.gyfnark.mongodb.net/?retryWrites=true&w=majority',
    function (에러, client) {
        if (에러) { return console.log(에러) }

        db = client.db('todoapp'); // todoapp 이라는 database(폴더)에 연결좀요. ..

        // db.collection('post').insertOne({이름:'John', 나이:20}, function(에러, 결과){
        //     console.log('저장완료');
        // });

        app.listen(80, function () {
            console.log('listening on 80')
        });
    })


// app.listen(80, function(){
//     console.log('listening on 80')
// });


// XX 경로로 들어오면 XX를 보내줌 ~ 만들기
// 누군가가 /pet 으로 방문을 하면
// pet 관련된 안내문을 띄워주자

app.get('/pet', function (요청, 응답) {
    응답.send('반갑습니다');
});

app.get('/', function (요청, 응답) {
    응답.sendFile(__dirname + '/index.html')
});

app.get('/write', function (요청, 응답) {
    응답.sendFile(__dirname + '/write.html')
});


// 어떤 사람이 /add 경로로 요청을 하면..
// ?? 를 해주세요~

app.post('/add', function (요청, 응답) {
    응답.send('전송완료')
    //console.log(요청.body.title)
    console.log(요청.body)

    db.collection('counter').findOne({ name: '게시물개수' }, function (에러, 결과) {
        console.log(결과.totalPost);
        var 총게시물개수 = 결과.totalPost; // 0

        var title = 요청.body.title;
        var date = 요청.body.date;

        // DB 에 저장해주세요.
        db.collection('post').insertOne({ _id: 총게시물개수 + 1, 제목: title, 날짜: date }, function (에러, 결과) {
            console.log('저장완료');
            // counter 콜렉션의 항목도 1 증가시켜야 함
            db.collection('counter').updateOne({ name: '게시물개수' }, { $inc: { totalPost: 1 } }, function (에러, 결과) {
                if (에러) { return console.log(에러) }
            })
        });

    });

    // var title = 요청.body.title;
    // var date = 요청.body.date;

    // // DB 에 저장해주세요.
    // db.collection('post').insertOne({ _id: 총게시물개수 + 1, 제목: title, 날짜: date }, function (에러, 결과) {
    //     console.log('저장완료');
    // });

});


// /list 로 GET 요청으로 접속하면
// 실제 DB에 저장된 데이터들로 예쁘게 꾸며진 HTML을 보여줌
app.get('/list', function (요청, 응답) {

    db.collection('post').find().toArray(function (에러, 결과) {
        console.log(결과);
        응답.render('list.ejs', { posts: 결과 });
    });



    //응답.render('list.ejs', { posts: 결과 })
    // 디비에 저장된 post라는 collection안의 모든 (id=?인, 이름이 ?인) 데이터를 꺼내주세요

});



