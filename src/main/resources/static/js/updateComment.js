
$('#updateCommentBtn').click(function() {
    $('#currentComment').css('display', "none");
    $('#modifyComment').css('display', "block");
    $('#btnBox').css('display', "none");
    $('#modifyBtnBox').css('display', "block");
})
$('#cancelBtn').click(function() {
    $('#currentComment').css('display', "block");
    $('#modifyComment').css('display', "none");
    $('#btnBox').css('display', "block");
    $('#modifyBtnBox').css('display', "none");
})



$(function check() {
    let chkContent = true;

    //빈값방지
    $('#modifyComment').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            chkContent = false;
        }else {
            chkContent = true;
        }
    })



    $('#doUpdateCommentBtn').click(function() {
        if (confirm("정말 수정하시겠습니까?") == true) {
            if (chkContent) {
                const boardSeq = $("#seq").val();
                const commentSeq = $('#commentSeq').val();
                const commentContent = $('#modifyComment').val();

                console.log("boardSeq :" + boardSeq)
                console.log("commentContent :" + commentContent)
                const comment = {
                    "seq" : commentSeq,
                    "comment": commentContent
                }


                $.ajax({
                    data: JSON.stringify(comment),
                    url: "/comment/user/update",
                    type: "PUT",
                    contentType: "application/json",
                    cache: false
                }).done(function (data, textStatus, xhr) {
                    console.log("xhr.status >>>>:" + xhr.status);
                    if (xhr.status === 200) {
                        console.log("done : " + data);
                        alert('댓글 수정이 완료되었습니다.')
                        const url = '/community/view/common/' + boardSeq;
                        location.href = url;
                    } else {
                        alert('댓글 수정 실패. 다시 시도해주세요.')
                    }
                }).fail(function (jqXHR, textStatus) {
                    alert('서버 통신 실패. 관리자에게 문의해주세요.');
                });

            } else {
                alert('입력정보를 확인해 주세요.');
            }
        }
    })



})
