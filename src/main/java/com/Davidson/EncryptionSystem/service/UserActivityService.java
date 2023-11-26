package com.Davidson.EncryptionSystem.service;

import com.Davidson.EncryptionSystem.model.Activity;
import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserActivityService {

    @Autowired
    private final UserActivityRepository userActivityRepository;

    @Cacheable(value = "allUserActivities")
    public List<UserActivity> findByUser(Users user) {
        return userActivityRepository.findByUser(user).orElseThrow(() -> new RuntimeException("No Activity for Specified User"));
    }

    @Cacheable(value = "singleActivity", key = "#title")
    public List<UserActivity> findActivity(String title, Users user){
        List<UserActivity> activities = findByUser(user);
        return activities.stream().filter(activity -> activity.getEncryptionTitle().equals(title)).collect(Collectors.toList());
    }


    @Cacheable(value = "getActivityByDate", key = "#id+ ' ' +#localDate")
    public List<UserActivity> getByDate(LocalDate localDate, Users user){
        List<UserActivity> activities = findByUser(user);
        return activities.stream().filter(activity -> activity.getDate().equals(localDate)).collect(Collectors.toList());
    }

    @Cacheable(value = "getActivityByType", key = "#id + ' ' + #activityString")
    public List<UserActivity> getActivityByType(Activity activityString, Users user){
        List<UserActivity> activities = findByUser(user);
        return activities.stream().filter(activity -> activity.getActivity().equals(activityString)).collect(Collectors.toList());
    }


    @CacheEvict(value = {"allUserActivities", "encryptionActivities", "getActivityByType", "getActivityByDate"}, allEntries = true)
    public ResponseEntity<UserActivity> postActivity(UserActivity userActivity){
        UserActivity createdActivity = userActivityRepository.save(userActivity);
        return new ResponseEntity<> (createdActivity, HttpStatus.CREATED);
    }


}
