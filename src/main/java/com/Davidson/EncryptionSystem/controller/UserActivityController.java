package com.Davidson.EncryptionSystem.controller;

import com.Davidson.EncryptionSystem.model.Activity;
import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/encryption")
public class UserActivityController {

    @Autowired
    private final UserActivityService userActivityService;

    @GetMapping("/activity/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> getUserActivities(@PathVariable long user_id){
        return userActivityService.findByUser(user_id);
    }

    @GetMapping("/activity-title/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByTitle(@PathVariable long user_id, @RequestBody String title){
        return userActivityService.findActivity(title, user_id);
    }

    @GetMapping("/activity-date/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByDate(@PathVariable long user_id, @RequestBody LocalDate date){
        return userActivityService.getByDate(date, user_id);
    }

    @GetMapping("/activity-type/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByType(@PathVariable long user_id, @RequestBody Activity activityString){
        return userActivityService.getActivityByType(activityString, user_id);
    }

    @GetMapping("activity/encryption")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> getAllEncryptionActivities(@PathVariable long user_id){
        return userActivityService.getEncryptionActivities(user_id);
    }

    @GetMapping("activity/encryption")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> getAllDecryptionActivities(@PathVariable long user_id){
        return userActivityService.getDecryptionActivities(user_id);
    }

    @PostMapping("/activity/encrypt")
    @ResponseBody
    @PreAuthorize("USER")
    public ResponseEntity<UserActivity> postEncryptionActivity(UserActivity userActivity){
        return userActivityService.postEncryptionActivity(userActivity);
    }

    @PostMapping("/activity/decrypt")
    @ResponseBody
    @PreAuthorize("USER")
    public ResponseEntity<UserActivity> postDecryptionActivity(UserActivity userActivity){
        return userActivityService.postDecryptionActivity(userActivity);
    }


}
