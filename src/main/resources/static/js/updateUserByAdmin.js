$("#statusYn").on('change', updateStatusYnFunc);
$("#adminYn").on('change', updateAdminYnFunc);

function updateStatusYnFunc() {
    const seq = $("#seq").text();
    const userId = $("#userId").text();
    const statusYn = $("#statusYn").val();
    const adminYn = $("#adminYn").val();

    let confirmMsg;
    if (statusYn === "N") {
        confirmMsg = userId+"을(를) 탈퇴 처리 하시겠습니까?";
    } else {
        confirmMsg = userId+"을(를) 탈퇴 취소 하시겠습니까?";
    }
    console.log("userId >>>> : " + userId);
    console.log("statusYn >>>> : " + statusYn);
    console.log("confirmMsg >>>> : " + confirmMsg);

    if (confirm(confirmMsg) == true){
        const user = {
            "seq" : seq,
            "statusYn" : statusYn,
            "adminYn" : adminYn
        }

        $.ajax({
            data : JSON.stringify(user),
            url : "/account/admin/update",
            type : "PUT",
            contentType: "application/json",
            cache: false
        }).done(function(data, textStatus, xhr) {
            console.log("xhr.status >>>>:" + xhr.status);
            if(xhr.status === 200){
                console.log("done : " + data);
                alert('처리 완료')
                let url = "/account/view/admin/detail/"+userId
                location.replace(url)
            }else{
                alert('처리 실패. 다시 시도해주세요.')
            }
        }).fail(function( jqXHR, textStatus ) {
            alert('서버 통신 실패. 관리자에게 문의해주세요.');
        });
    }else{
        return
    }
}

function updateAdminYnFunc() {
    const seq = $("#seq").text();
    const userId = $("#userId").text();
    const statusYn = $("#statusYn").val();
    const adminYn = $("#adminYn").val();

    let confirmMsg;
    if (adminYn === "Y") {
        confirmMsg = userId+"을(를) 관리자로 지정 하시겠습니까?";
    } else {
        confirmMsg = userId+"을(를) 일반 유저로 변경 하시겠습니까?";
    }
    console.log("userId >>>> : " + userId);
    console.log("adminYn >>>> : " + adminYn);
    console.log("confirmMsg >>>> : " + confirmMsg);

    if (confirm(confirmMsg) == true){
        const user = {
            "seq" : seq,
            "statusYn" : statusYn,
            "adminYn" : adminYn
        }

        $.ajax({
            data : JSON.stringify(user),
            url : "/account/admin/update",
            type : "PUT",
            contentType: "application/json",
            cache: false
        }).done(function(data, textStatus, xhr) {
            console.log("xhr.status >>>>:" + xhr.status);
            if(xhr.status === 200){
                console.log("done : " + data);
                alert('처리 완료')
                let url = "/account/view/admin/detail/"+userId
                location.replace(url)
            }else{
                alert('처리 실패. 다시 시도해주세요.')
            }
        }).fail(function( jqXHR, textStatus ) {
            alert('서버 통신 실패. 관리자에게 문의해주세요.');
        });
    }else{
        return
    }
}







