$(function check() {
    let chkId = false, chkPw = false

    //아이디 공백 체크
    $('#userId').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            $('#idCheck').text("아이디를 입력하세요.");
            chkId = false;
        }else {
           chkId = true;
        }
    })

    //비밀번호 공백 체크
    $('#userPw').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            $('#pwCheck').text("비밀번호를 입력하세요.");
            chkPw = false;
        }else{
            chkPw = true;
        }
    })


    $('#loginBtn').click(function() {
        console.log(
            "로그인 버튼 클릭!"
        )
        if(chkId && chkPw){
            const id = $("#userId").val();
            const pw = $('#userPw').val();
            console.log("id>>>:" + id);
            console.log("pw>>>:" + pw);
            const user ={
                "username" : id,
                "password" : pw
            }

            $.ajax({
                url: '/login-authentication',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                data: user,
            }).done(function(res){
                if(res.code=='200'){
                    console.log(res);
                    alert("로그인 되었습니다.");
                    location.replace('/')
                } else {
                    alert(res.message);
                }
                console.log(res);
            }).fail(function(){
                alert('서버 통신 실패. 관리자에게 문의해주세요.');
            });
        }else {
            alert('입력정보를 확인해 주세요.');
        }
    })
})



