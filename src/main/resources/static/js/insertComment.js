$(function check() {
    let chkContent = false;


    //빈값방지
    $('#commentContent').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            chkContent = false;
        }else {
            chkContent = true;
        }
    })




    $('#insertReplyBtn').click(function() {

        if(chkContent){
            const boardSeq = $("#seq").val();
            const commentContent = $('#commentContent').val();

            const comment ={
                "boardSeq" : boardSeq,
                "comment" : commentContent
            }

            $.ajax({
                data : JSON.stringify(comment),
                url : "/comment/user/insert",
                type : "POST",
                contentType: "application/json",
                cache: false
            }).done(function(data, textStatus, xhr) {
                console.log("xhr.status >>>>:" + xhr.status);
                if(xhr.status === 200){
                    console.log("done : " + data);
                    alert('댓글 등록이 완료되었습니다.')
                    let url = '/community/view/common/'+boardSeq;
                    location.replace(url)
                }else{
                    alert('댓글 등록 실패. 다시 시도해주세요.')
                }
            }).fail(function( jqXHR, textStatus ) {
                alert('서버 통신 실패. 관리자에게 문의해주세요.');
            });

        }else {
            alert('입력정보를 확인해 주세요.');
        }

    })


})



