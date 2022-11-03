$(function(){
    goSearch = function(page) {
        if (page == null || page == undefined) {
            page = 1;
        } else if (page == 0) {
            alert('마지막 페이지입니다.');
            return;
        }
        $('#page').val(page);
        $('#get_community_form').submit();
    };
});