package ru.tms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tms.DTO.AddCommentRequestDTO;
import ru.tms.DTO.AddCommentResponseDTO;
import ru.tms.DTO.AuthRequest;
import ru.tms.DTO.EditTaskRequestDTO;
import ru.tms.DTO.EditTaskResponseDTO;
import ru.tms.DTO.NewTaskRequestDTO;
import ru.tms.DTO.NewTaskResponseDTO;
import ru.tms.entity.UserInfo;
import ru.tms.security.JwtService;
import ru.tms.security.UserInfoService;
import ru.tms.service.TaskService;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final TaskService taskService;
    @Autowired
    private UserInfoService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    /**
     * пользователь добавляет себе новую задачу
     *
     * @param newTaskRequestDTO
     * @return
     */
    @PostMapping("/newTask")
    public ResponseEntity<NewTaskResponseDTO> createNewTask(@RequestBody NewTaskRequestDTO newTaskRequestDTO) {
        return new ResponseEntity<>(taskService.createNewTask(newTaskRequestDTO), HttpStatus.CREATED);
    }

    /**
     * пользователь редактирует свои задачи (поменять статус или назначить исполнителей)
     * @param editTaskRequestDTO
     * @return
     */
    @PatchMapping("/editTask")
    public ResponseEntity<EditTaskResponseDTO> editTask(@RequestBody EditTaskRequestDTO editTaskRequestDTO) {
        return new ResponseEntity<>(taskService.editTask(editTaskRequestDTO), HttpStatus.CREATED);
    }

    /**пользователь удаляет свою задачу
     * @param idTask
     * @return
     */
    @DeleteMapping("/deleteTask/{idTask}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer idTask) {
        taskService.deleteTask(idTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/addComment")
    public ResponseEntity<AddCommentResponseDTO> editTask(@RequestBody AddCommentRequestDTO addCommentRequestDTO) {
        return new ResponseEntity<>(taskService.addComment(addCommentRequestDTO), HttpStatus.CREATED);
    }


//    @GetMapping("/admin/adminProfile")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String adminProfile() {
//        return "Welcome to Admin Profile";
//    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        log.info("вызвался этот метод");
        log.info(authRequest.getUserName());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        log.info(authentication.getName());
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }


}
