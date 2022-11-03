$(function check() {
    let chkTitle = false, chkContent = false;


    //빈값방지
    $('#title').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            //$('#idCheck').text("아이디를 입력하세요.");
            chkTitle = false;
        }else {
            chkTitle = true;
        }
    })
    $('#content').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            //$('#idCheck').text("아이디를 입력하세요.");
            chkContent = false;
        }else {
            chkContent = true;
        }
    })




    $('#insertBtn').click(function() {

        if(chkTitle && chkContent){
            const title = $('#title').val();
            const content = $('#content').val();

            const notice ={
                "title" : title,
                "content" : content
            }

            $.ajax({
                data : JSON.stringify(notice),
                url : "/notice/admin/insert",
                type : "POST",
                contentType: "application/json",
                cache: false
            }).done(function(data, textStatus, xhr) {
                console.log("xhr.status >>>>:" + xhr.status);
                if(xhr.status === 200){
                    console.log("done : " + data);
                    alert('게시글 등록이 완료되었습니다.')
                    location.replace('/notice/view/common/list')
                }else{
                    alert('게시글 등록 실패. 다시 시도해주세요.')
                }
            }).fail(function( jqXHR, textStatus ) {
                alert('서버 통신 실패. 관리자에게 문의해주세요.');
            });

        }else {
            alert('입력정보를 확인해 주세요.');
        }

    })


})



