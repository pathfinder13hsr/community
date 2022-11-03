$(function check() {
    const getPwCheck = RegExp(/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/);
    const getEmailCheck = RegExp(/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$/);
    const getNicknameCheck = RegExp(/^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$/);

    // chkPw = false, chkPwConfirm = false, chkEmail = false, chkNick = false;
    chkPw = true, chkPwConfirm = true, chkEmail = true, chkNick = true;

    // 비밀번호 변경,확인 입력창에 아무런 이벤트도 발생하지 않았을 때(수정의사 없음)
    if ($('#userPw').val() === '') {//입력값이 없을 때
        $('#pwCheck').text("입력하지 않으면 기존 비밀번호를 계속 사용합니다.");
        chkPw = true;
        chkPwConfirm = true;
        console.log("?????"+chkPw+chkPwConfirm);
    }
    //비밀번호 유효성체크
    $('#userPw').keyup(function() {
        if ($(this).val() === '') {//뭔가 입력했다가 다 지웠을 때
            $('#pwCheck').text("입력하지 않으면 기존 비밀번호를 계속 사용합니다.");
            $('#pwConfirm').text("")
            $('#pwLabel').css('display', "none");
            $('#userPwConfirm').prop('type', "hidden");
            chkPw = true;
            chkPwConfirm = true;
        }else if(!getPwCheck.test($(this).val())) {//정규표현식이 틀렸을 때
            $('#pwCheck').text("비밀번호는 8~16자 영문 대소문자, 숫자, 특수문자를 모두 사용하세요.")
            chkPw = false;
        }else{
            $('#pwCheck').text("사용가능한 비밀번호입니다.")
            $('#pwLabel').css('display', "block");
            $('#userPwConfirm').prop('type', "password");
            chkPw = true;
        }

        console.log("비밀번호 유효성체크 chkPw :"+chkPw+"chkPwConfirm"+chkPwConfirm);
    })
    //비밀번호 확인
    $('#userPwConfirm').keyup(function() {
        if ($(this).val() === '' && $('#userPw').val() === ''){
            chkPwConfirm = true;
        }else if($(this).val() === $('#userPw').val()) {
            $('#pwConfirm').text("비밀번호가 일치합니다");
            chkPwConfirm = true;
        }else{
            $('#pwConfirm').text("비밀번호가 일치하지 않습니다. 다시 확인하세요.")
            chkPwConfirm = false;
        }
        console.log("chkPwConfirm"+chkPwConfirm)
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
                    }else if (result === 'duplicate'){
                        if($('#userEmail').val() === $('#currentEmail').val()){
                            $('#emailCheck').text("기존 이메일을 계속 사용합니다.");
                            chkEmail = true;
                        }else{
                            $('#emailCheck').text("이미 사용중인 이메일입니다.");
                            chkEmail = false;
                        }
                    }
                },
                error : function (status, error) { //통신에 실패했을때
                    console.log('통신실패');
                    console.log(status, error)
                }
            });
        }
        console.log("chkEmail"+chkEmail)
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
                    }else if (result === 'duplicate'){
                        if ($('#userNickname').val() === $('#currentNickname').val()){
                            $('#nicknameCheck').text("기존 닉네임을 계속 사용합니다.");
                            chkNick = true;
                        }else {
                            $('#nicknameCheck').text("이미 사용중인 닉네임입니다.");
                            chkNick = false;
                        }
                    }
                },
                error : function (status, error) { //통신에 실패했을때
                    console.log('통신실패');
                    console.log(status, error)
                }
            });
        }
        console.log("chkNick"+chkNick)
    })



    $('#updateBtn').click(function() {
        console.log("마지막 체크>>>>"+chkPw+chkPwConfirm+chkEmail+chkNick)
        if (($('#userPw').val() === $('#currentPw').val() || $('#userPw').val() === '') &&
            $('#userEmail').val() === $('#currentEmail').val() &&
            $('#userNickname').val() === $('#currentNickname').val()){
                alert("수정된 정보가 없습니다. 마이페이지로 돌아갑니다.")
                location.replace("/account/view/user/detail/"+$('#userId').val());
                console.log("마이페이지로 리턴")
        }else if(chkPw && chkPwConfirm && chkEmail && chkNick){
            console.log("업데이트 ajax 시작")
            const seq = $('#userSeq').val();
            const id = $('#userId').val();
            let pw;
            if($('#userPw').val() === ''){
                pw = $('#currentPw').val();
            }else {
                pw = $('#userPw').val();
            }
            const email = $('#userEmail').val();
            const nickName = $('#userNickname').val();

            const user ={
                "seq" : seq,
                "userId" : id,
                "userPw" : pw,
                "userEmail" : email,
                "userNickname" : nickName
            }

            $.ajax({
                data : JSON.stringify(user),
                url : "/account/user/update",
                type : "PUT",
                contentType: "application/json",
                cache: false
            }).done(function(data, textStatus, xhr) {
                console.log("xhr.status >>>>:" + xhr.status);
                if(xhr.status === 200){
                    console.log("done : " + data);
                    alert('회원정보 수정이 완료되었습니다.')
                    location.replace('/')
                }else{
                    alert('회원정보 수정 실패. 다시 시도해주세요.')
                }
            }).fail(function( jqXHR, textStatus ) {
                alert('서버 통신 실패. 관리자에게 문의해주세요.');
            });

        }else {
            alert('입력정보를 확인해 주세요.');
        }

    })


})



