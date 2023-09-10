package com.Davidson.EncryptionSystem.service;

import com.Davidson.EncryptionSystem.model.Activity;
import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActivityService {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Cacheable(value = "allUserActivities", key = "#id")
    public List<UserActivity> findByUser(long user_id){
        return userActivityRepository.findByUser(user_id).orElseThrow(()-> new RuntimeException("No Activity for Specified User"));
    }

    @Cacheable(value = "singleActivity", key = "#id+ ' ' +#title")
    public List<UserActivity> findActivity(String title, long user_id){
        List<UserActivity> activities = findByUser(user_id);
        return activities.stream().filter(activity -> activity.getEncryptionTitle().equals(title)).collect(Collectors.toList());
    }


    @Cacheable(value = "getActivityByDate", key = "#id+ ' ' +#localDate")
    public List<UserActivity> getByDate(LocalDate localDate, long user_id){
        List<UserActivity> activities = findByUser(user_id);
        return activities.stream().filter(activity -> activity.getDate().equals(localDate)).collect(Collectors.toList());
    }

    @Cacheable(value = "getActivityByType", key = "#id + ' ' + #activityString")
    public List<UserActivity> getActivityByType(Activity activityString, long user_id){
        List<UserActivity> activities = findByUser(user_id);
        return activities.stream().filter(activity -> activity.getActivity().equals(activityString)).collect(Collectors.toList());
    }

    @Cacheable(value = "encryptionActivities")
    public List<UserActivity> getEncryptionActivities(long user_id){
        return findByUser(user_id).stream()
                .filter(activity -> activity.getActivity()
                        .equals(Activity.ENCRYPT)).collect(Collectors.toList());
    }

    @Cacheable(value = "decryptionActivities")
    public List<UserActivity> getDecryptionActivities(long user_id){
        return findByUser(user_id).stream()
                .filter(activity -> activity.getActivity()
                        .equals(Activity.DECRYPT)).collect(Collectors.toList());
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
