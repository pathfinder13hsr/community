$("#mypageBtn").on('click', mypageFunc);

function mypageFunc() {
        const userId = $("#currentUserId").text();

        $.ajax({
            url : "/account/view/user/detail/"+userId,
            type : "GET",
            // data : JSON.stringify(notice),
            data : {"userId" : userId},
            contentType: "application/json",
            cache: false
        });

        // $.ajax({
        //     url : "/account/view/user/detail/"+userId,
        //     type : "GET",
        //     // data : JSON.stringify(notice),
        //     data : {"userId" : userId},
        //     contentType: "application/json",
        //     cache: false
        // // }).done(function(data, textStatus, xhr) {
        // }).done(function(res) {
        //     // console.log("xhr.status >>>>:" + xhr.status);
        //     if(res.status === 200){
        //         console.log("done : " + data);
        //     }else{
        //         alert('다시 시도해주세요.')
        //     }
        // // }).fail(function(jqXHR, textStatus) {
        // }).fail(function(res) {
        //     console.log("res>>>>"+res.status)
        //     alert('서버 통신 실패. 관리자에게 문의해주세요.');
        // });

}
