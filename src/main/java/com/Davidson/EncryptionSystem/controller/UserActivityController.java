package com.Davidson.EncryptionSystem.controller;

import com.Davidson.EncryptionSystem.model.Activity;
import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.model.Users;
<<<<<<< Updated upstream
import com.Davidson.EncryptionSystem.repository.UserRepository;
import com.Davidson.EncryptionSystem.service.UserActivityService;
import lombok.RequiredArgsConstructor;
=======
import com.Davidson.EncryptionSystem.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Stashed changes
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
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
=======
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
>>>>>>> Stashed changes
    }

    @GetMapping("/activity-title/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
<<<<<<< Updated upstream
    public List<UserActivity> findActivitiesByTitle(@PathVariable Long user_id, @RequestBody String title){
        Users user = userRepository.findById(user_id).orElseThrow();
        return userActivityService.findActivity(title, user);
=======
    public List<UserActivity> findActivitiesByTitle(@PathVariable long user_id, @RequestBody String title){
        return userActivityService.findActivity(title, user_id);
>>>>>>> Stashed changes
    }

    @GetMapping("/activity-date/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
<<<<<<< Updated upstream
    public List<UserActivity> findActivitiesByDate(@PathVariable Long user_id, @RequestBody LocalDate date){
        Users user = userRepository.findById(user_id).orElseThrow();
        return userActivityService.getByDate(date, user);
=======
    public List<UserActivity> findActivitiesByDate(@PathVariable long user_id, @RequestBody LocalDate date){
        return userActivityService.getByDate(date, user_id);
>>>>>>> Stashed changes
    }

    @GetMapping("/activity-type/{user_id}")
    @ResponseBody
    @PreAuthorize("USER")
<<<<<<< Updated upstream
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
=======
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
>>>>>>> Stashed changes
    }


}
