$(function check() {
    const getIdCheck = RegExp(/^[a-zA-Z0-9]{4,20}$/);
    const getPwCheck = RegExp(/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/);
    const getEmailCheck = RegExp(/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$/);
    const getNicknameCheck = RegExp(/^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$/);

    let chkId = false, chkPw = false, chkPwConfirm = false, chkEmail = false, chkNick = false;


    //아이디 유효성 체크
    $('#userId').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            $('#idCheck').text("아이디를 입력하세요.");
            chkId = false;
        }else if(!getIdCheck.test($(this).val())) {//정규표현식이 틀렸을 때
            $('#idCheck').text("아이디는 4~20자의 영문 대소문자, 숫자만 입력 가능합니다.")
            chkId = false;
        }else {
            const id = $(this).val();
            $.ajax({
                type :'post', // 서버에 전송하는 http방식
                url :'/account/checkId', // 서버 요청 url
                headers : {
                    'Content-Type' : 'application/json'
                },
                dataType : 'text', //서버로 부터 응답받을 데이터의 형태
                data : id, // 서버로 전송할 데이터 // 위에서 지정한 const id
                success : function(result) { // 매개변수에 통신성공시 데이터가 저장된다.
                    //서버와 통신성공시 실행할 내용 작성.
                    console.log('통신 성공!' + result);
                    if(result === 'available'){
                        $('#idCheck').text("사용가능한 아이디입니다.");
                        chkId = true;
                    }else{
                        $('#idCheck').text("이미 사용중인 아이디입니다.");
                        chkId = false;
                    }
                },
                error : function (status, error) { //통신에 실패했을때
                    console.log('통신실패');
                    console.log(status, error)
                }
            });
        }
    })

    //비밀번호 유효성체크
    $('#userPw').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            $('#pwCheck').text("비밀번호를 입력하세요.");
            chkPw = false;
        }else if(!getPwCheck.test($(this).val())) {//정규표현식이 틀렸을 때
            $('#pwCheck').text("비밀번호는 8~16자 영문 대소문자, 숫자, 특수문자를 모두 사용하세요.")
            chkPw = false;
        }else{
            $('#pwCheck').text("사용가능한 비밀번호입니다.")
            chkPw = true;
        }
    })
    //비밀번호 확인
    $('#userPwConfirm').keyup(function() {
        if ($(this).val() === $('#userPw').val()) {
            $('#pwConfirm').text("비밀번호가 일치합니다");
            chkPwConfirm = true;
        }else{
            $('#pwConfirm').text("비밀번호가 일치하지 않습니다. 다시 확인하세요.")
            chkPwConfirm = false;
        }
    })


    //이메일 유효성체크
    $('#userEmail').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            $('#emailCheck').text("이메일을 입력하세요.");
            chkEmail = false;
        }else if(!getEmailCheck.test($(this).val())) {//정규표현식이 틀렸을 때
            $('#emailCheck').text("이메일 형식에 맞춰 입력하세요")
            chkEmail = false;
        }else {
            const email = $(this).val();
            $.ajax({
                type :'post', // 서버에 전송하는 http방식
                url :'/account/checkEmail', // 서버 요청 url
                headers : {
                    'Content-Type' : 'application/json'
                },
                dataType : 'text', //서버로 부터 응답받을 데이터의 형태
                data : email, // 서버로 전송할 데이터 // 위에서 지정한 const id
                success : function(result) { // 매개변수에 통신성공시 데이터가 저장된다.
                    //서버와 통신성공시 실행할 내용 작성.
                    console.log('통신 성공!' + result);
                    if(result === 'available'){
                        $('#emailCheck').text("사용가능한 이메일입니다.");
                        chkEmail = true;
                    }else{
                        $('#emailCheck').text("이미 사용중인 이메일입니다.");
                        chkEmail = false;
                    }
                },
                error : function (status, error) { //통신에 실패했을때
                    console.log('통신실패');
                    console.log(status, error)
                }
            });
        }
    })

    //닉네임 유효성검증
    $('#userNickname').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            $('#nicknameCheck').text("닉네임을 입력하세요.");
            chkNick = false;
        }else if(!getNicknameCheck.test($(this).val())) {//정규표현식이 틀렸을 때
            $('#nicknameCheck').text("특수문자를 제외한 2~10자리로 입력하세요.")
            chkNick = false;
        }else {
            const nickname = $(this).val();
            $.ajax({
                type :'post', // 서버에 전송하는 http방식
                url :'/account/checkNickname', // 서버 요청 url
                headers : {
                    'Content-Type' : 'application/json'
                },
                dataType : 'text', //서버로 부터 응답받을 데이터의 형태
                data : nickname, // 서버로 전송할 데이터 // 위에서 지정한 const id
                success : function(result) { // 매개변수에 통신성공시 데이터가 저장된다.
                    //서버와 통신성공시 실행할 내용 작성.
                    console.log('통신 성공!' + result);
                    if(result === 'available'){
                        $('#nicknameCheck').text("사용가능한 닉네임입니다.");
                        chkNick = true;
                    }else{
                        $('#nicknameCheck').text("이미 사용중인 닉네임입니다.");
                        chkNick = false;
                    }
                },
                error : function (status, error) { //통신에 실패했을때
                    console.log('통신실패');
                    console.log(status, error)
                }
            });
        }
    })



    $('#signupBtn').click(function() {

        if(chkId && chkPw && chkPwConfirm && chkEmail && chkNick){
            const id = $("#userId").val();
            const pw = $('#userPw').val();
            const email = $('#userEmail').val();
            const nickName = $('#userNickname').val();
            //프로퍼티 이름은 반드시 userVO와 같아야 동작이 된다!! 매우 중요!
            const user ={
                "userId" : id,
                "userPw" : pw,
                "userEmail" : email,
                "userNickname" : nickName
            }

            $.ajax({
                data : JSON.stringify(user),
                url : "/account/signup",
                type : "POST",
                contentType: "application/json",
                cache: false
            }).done(function(data, textStatus, xhr) {
                console.log("xhr.status >>>>:" + xhr.status);
                if(xhr.status === 200){
                    console.log("done : " + data);
                    alert('회원가입이 완료되었습니다.')
                    location.replace('/')
                }else{
                    alert('회원가입 실패. 다시 시도해주세요.')
                }
            }).fail(function( jqXHR, textStatus ) {
                alert('서버 통신 실패. 관리자에게 문의해주세요.');
            });

        }else {
            alert('입력정보를 확인해 주세요.');
        }

    })


})



