package com.Davidson.EncryptionSystem.controller;

import com.Davidson.EncryptionSystem.model.Activity;
import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.repository.UserActivityRepository;
import com.Davidson.EncryptionSystem.repository.UserRepository;
import com.Davidson.EncryptionSystem.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/encryption")
@RequiredArgsConstructor
@CrossOrigin
public class UserActivityController {

    private final UserActivityService userActivityService;
    private final UserRepository userRepository;
    private final UserActivityRepository userActivityRepository;

    @GetMapping("/activity/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('Role_USER') and #id == authentication.principal.id and user.username = authorization.name")
    public List<UserActivity> getUserActivities(@PathVariable Long id){
        Users user = userRepository.findById(id).orElseThrow();
        Comparator<UserActivity> dateComp = (a,b) -> (int)b.getId() - (int)(a.getId());
        return userActivityService.findByUser(user).stream()
                .sorted(dateComp).collect(Collectors.toList());
    }

    @GetMapping("/activity")
    public Page<UserActivity> pagedActivities(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return userActivityRepository.findAll(pageable);
    }

    @GetMapping("/activity-title/{id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByTitle(@PathVariable Long id, @RequestBody String title){
        Users user = userRepository.findById(id).orElseThrow();
        return userActivityService.findActivity(title, user);
    }

    @GetMapping("/activity-date/{id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByDate(@PathVariable Long id, @RequestBody LocalDate date){
        Users user = userRepository.findById(id).orElseThrow();
        return userActivityService.getByDate(date, user);
    }

    @GetMapping("/activity-type/{id}")
    @ResponseBody
    @PreAuthorize("USER")
    public List<UserActivity> findActivitiesByType(@PathVariable Long id, @RequestBody Activity activityString){
        Users user = userRepository.findById(id).orElseThrow();
        return userActivityService.getActivityByType(activityString, user);
    }

    @GetMapping("/encrypted/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('Role_USER') and #id == authentication.principal.id")
    public List<UserActivity> getAllEncryptionActivities(@PathVariable Long id){
        Users user = userRepository.findById(id).orElseThrow();
        return userActivityService.findByUser(user).stream()
                .filter(userActivity -> userActivity.getActivity().equals(Activity.ENCRYPT))
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .collect(Collectors.toList());
    }

    @GetMapping("/decrypted/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('Role_USER') and #id == authentication.principal.id")
    public List<UserActivity> getAllDecryptionActivities(@PathVariable Long id){
        Users user = userRepository.findById(id).orElseThrow();
        return userActivityService.findByUser(user).stream()
                .filter(userActivity -> userActivity.getActivity().equals(Activity.DECRYPT))
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .collect(Collectors.toList());
    }

    @PostMapping("/encrypt/{id}")
    @PreAuthorize("hasRole('Role_USER') and #id == authentication.principal.id")
    public ResponseEntity<UserActivity> postEncryptionActivity(@RequestBody @Valid UserActivity userActivity,
                                                               @PathVariable Long id ) {
        Users user = userRepository.findById(id).orElseThrow();
        userActivity.setUser(user);
        userActivity.setActivity(Activity.ENCRYPT);
        userActivity.setDate(LocalDate.now());
        return userActivityService.postActivity(userActivity);
    }

    @PostMapping("/decrypt/{id}")
    @PreAuthorize("hasRole('Role_USER') and #id == authentication.principal.id")
    public ResponseEntity<UserActivity> postDecryptionActivity(@RequestBody @Valid UserActivity userActivity,
                                                               @PathVariable Long id ) {
        Users user = userRepository.findById(id).orElseThrow();
        userActivity.setUser(user);
        userActivity.setActivity(Activity.DECRYPT);
        userActivity.setDate(LocalDate.now());
        return userActivityService.postActivity(userActivity);
    }



}
