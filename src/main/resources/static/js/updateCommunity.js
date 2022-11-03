
$(function check() {
    let chkTitle = true, chkContent = true;


    //빈값방지
    $('#title').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            chkTitle = false;
        }else {
            chkTitle = true;
        }
    })
    $('#content').keyup(function() {
        if ($(this).val() === '') {//입력값이 없을 때
            chkContent = false;
        }else {
            chkContent = true;
        }
    })


    $('#updateBtn').click(function() {

        if(chkTitle && chkContent){
            const seq = $('#seq').val();
            const title = $('#title').val();
            const content = $('#content').val();

            console.log("seq :" +seq)
            console.log("title :" + title)
            console.log("content :" + content)
            const community ={
                "seq" : seq,
                "title" : title,
                "content" : content
            }


            $.ajax({
                data : JSON.stringify(community),
                url : "/community/user/update",
                type : "PUT",
                contentType: "application/json",
                cache: false
            }).done(function(data, textStatus, xhr) {
                console.log("xhr.status >>>>:" + xhr.status);
                if(xhr.status === 200){
                    console.log("done : " + data);
                    alert('게시글 수정이 완료되었습니다.')
                    const url='/community/view/common/'+seq;
                    location.href=url;
                }else{
                    alert('게시글 수정 실패. 다시 시도해주세요.')
                }
            }).fail(function( jqXHR, textStatus ) {
                alert('서버 통신 실패. 관리자에게 문의해주세요.');
            });

        }else {
            alert('입력정보를 확인해 주세요.');
        }

    })


})
