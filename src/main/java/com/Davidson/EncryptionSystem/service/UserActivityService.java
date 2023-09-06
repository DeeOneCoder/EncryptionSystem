package com.Davidson.EncryptionSystem.service;

import com.Davidson.EncryptionSystem.model.Activity;
import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActivityService {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Cacheable("allUserActivities")
    public List<UserActivity> getAllActivities(){
        return userActivityRepository.findAll();
    }

    @Cacheable("getActivityByDate")
    public List<UserActivity> getByDate(@RequestBody LocalDate localDate){
        return userActivityRepository.findByDate(localDate)
                .orElseThrow(() -> new RuntimeException("No Activity for Such Date"));
    }

    @Cacheable("getActivityByType")
    public List<UserActivity> getActivityByType(@RequestBody String activity){
        return userActivityRepository.findByActivity(activity)
                .orElseThrow(() -> new RuntimeException("No Activity for Such Date"));
    }

    @Cacheable("encryptionActivities")
    public List<UserActivity> getEncryptionActivities(){
        return userActivityRepository.findAll()
                .stream()
                .filter(userActivity -> userActivity.getActivity().equals(Activity.ENCRYPT))
                .collect(Collectors.toList());
    }

    @Cacheable("decryptionActivities")
    public List<UserActivity> getDecryptionActivities(){
        return userActivityRepository.findAll()
                .stream()
                .filter(userActivity -> userActivity.getActivity().equals(Activity.DECRYPT))
                .collect(Collectors.toList());
    }

    @CacheEvict(value = {"allUserActivities", "encryptionActivities", "getActivityByType", "getActivityByDate"}, allEntries = true)
    public ResponseEntity<UserActivity> postEncryptionActivity(UserActivity userActivity){
        userActivity.setActivity(Activity.ENCRYPT);
        return ResponseEntity.ok(userActivityRepository.save(userActivity));
    }

    @CacheEvict(value = {"allUserActivities", "decryptionActivities", "getActivityByType", "getActivityByDate"}, allEntries = true)
    public ResponseEntity<UserActivity> postDecryptionActivity(UserActivity userActivity){
        userActivity.setActivity(Activity.DECRYPT);
        return ResponseEntity.ok(userActivityRepository.save(userActivity));
    }

}
