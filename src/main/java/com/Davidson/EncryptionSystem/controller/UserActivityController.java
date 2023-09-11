package com.Davidson.EncryptionSystem.controller;

import com.Davidson.EncryptionSystem.model.Activity;
import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.repository.UserRepository;
import com.Davidson.EncryptionSystem.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/encryption")
@RequiredArgsConstructor
public class UserActivityController {

    private final UserActivityService userActivityService;
    private final UserRepository userRepository;

    @GetMapping("/activity/{id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> getUserActivities(@PathVariable Long id){
        Users user = userRepository.findById(id).orElseThrow();
        return userActivityService.findByUser(user);
    }

    @GetMapping("/activity-title/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByTitle(@PathVariable Long user_id, @RequestBody String title){
        Users user = userRepository.findById(user_id).orElseThrow();
        return userActivityService.findActivity(title, user);
    }

    @GetMapping("/activity-date/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByDate(@PathVariable Long user_id, @RequestBody LocalDate date){
        Users user = userRepository.findById(user_id).orElseThrow();
        return userActivityService.getByDate(date, user);
    }

    @GetMapping("/activity-type/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByType(@PathVariable Long user_id, @RequestBody Activity activityString){
        Users user = userRepository.findById(user_id).orElseThrow();
        return userActivityService.getActivityByType(activityString, user);
    }

    @GetMapping("/encrypted")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> getAllEncryptionActivities(@PathVariable Long user_id){
        Users user = userRepository.findById(user_id).orElseThrow();
        return userActivityService.getEncryptionActivities(user);
    }

    @GetMapping("/decrypted")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> getAllDecryptionActivities(@PathVariable Long user_id){
        Users user = userRepository.findById(user_id).orElseThrow();
        return userActivityService.getDecryptionActivities(user);
    }

    @PostMapping("/encrypt/{user_id}")
    @PreAuthorize("USER")
    public ResponseEntity<UserActivity> postEncryptionActivity(@RequestBody @Valid UserActivity userActivity,
                                                               @PathVariable Long user_id ) throws Exception {
        Users user = userRepository.findById(user_id).orElseThrow();
        userActivity.setUser(user);
        userActivity.setActivity(Activity.ENCRYPT);
        userActivity.setDate(LocalDate.now());
        return userActivityService.postActivity(userActivity);
    }

    @PostMapping("/decrypt/{user_id}")
    @PreAuthorize("USER")
    public ResponseEntity<UserActivity> postDecryptionActivity(@RequestBody @Valid UserActivity userActivity,
                                                               @PathVariable Long user_id ) throws Exception {
        Users user = userRepository.findById(user_id).orElseThrow();
        userActivity.setUser(user);
        userActivity.setActivity(Activity.DECRYPT);
        userActivity.setDate(LocalDate.now());
        return userActivityService.postActivity(userActivity);
    }


}
