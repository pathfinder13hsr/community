package com.quiz.license.user.service;

import com.quiz.license.notice.domain.entity.NoticeEntity;
import com.quiz.license.user.domain.dto.AccountDto;
import com.quiz.license.user.domain.entity.AccountEntity;
import com.quiz.license.user.domain.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountService {
    private final AccountRepository userRepository;

    public AccountService(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public List<AccountDto> getUserList() {
//        List<AccountEntity> userEntityList = userRepository.findAll();
//        List<AccountDto> userDtoList = new ArrayList<>();
//        for (AccountEntity entity : userEntityList) {
//            AccountDto accountDto = new AccountDto(entity);
//            userDtoList.add(accountDto);
//        }
//        return userDtoList;
//    }

    public Page<AccountEntity> getUserListPageable(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 10, Sort.by("seq").descending());
        return userRepository.findAll(pageable);
    }

    public void insertUser(AccountDto userDto) {
        if (!userRepository.existsByUserId(userDto.getUserId())
                && !userRepository.existsByUserEmail(userDto.getUserEmail())
                && !userRepository.existsByUserNickname(userDto.getUserNickname())) {
            AccountEntity accountEntity = userDto.byInsert();
            log.info("userRepository.save(accountEntity) : {}", accountEntity);
            userRepository.save(accountEntity);
        }
    }


    public Boolean checkId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public Boolean checkEmail(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    public Boolean checkNickname(String userNickname) {
        return userRepository.existsByUserNickname(userNickname);
    }


    public void updateUser(AccountDto accountParam) {
        AccountEntity selectData = userRepository.findBySeq(accountParam.getSeq());
        userRepository.save(accountParam.byUpdate(selectData));
    }

    public void updateUserByAdmin(AccountDto accountParam) {
        AccountEntity selectData = userRepository.findBySeq(accountParam.getSeq());
        userRepository.save(accountParam.byUpdateByAdmin(selectData));
    }

    public AccountDto getUserDetail(String userId) {

        AccountEntity userEntity = userRepository.findByUserId(userId);
        AccountDto userDto = new AccountDto(userEntity);
    return userDto;
}

}
