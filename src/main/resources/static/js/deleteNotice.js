$("#deleteBtn").on('click', deleteFunc);

function deleteFunc() {
    if (confirm("정말 삭제하시겠습니까?") == true){
        const seq = $("#seq").val();

        console.log("seq >>>> : " + seq);

        $.ajax({
            url : "/notice/admin/delete/"+seq,
            type : "DELETE"
        }).done(function(data, textStatus, xhr) {
            console.log("xhr.status >>>>:" + xhr.status);
            if(xhr.status === 200){
                console.log("done : " + data);
                alert('삭제 완료')
                location.replace('/notice/view/common/list')
            }else{
                alert('삭제 실패. 다시 시도해주세요.')
            }
        }).fail(function( jqXHR, textStatus ) {
            alert('서버 통신 실패. 관리자에게 문의해주세요.');
        });
    }else{
        return
    }
}

