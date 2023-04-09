package gam.jangseop.dbadmin.auth.Controller;

import gam.jangseop.dbadmin.auth.domain.Admin;
import gam.jangseop.dbadmin.auth.dto.SignRequest;
import gam.jangseop.dbadmin.auth.dto.SignResponse;
import gam.jangseop.dbadmin.auth.repository.AdminRepository;
import gam.jangseop.dbadmin.auth.service.SignService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SignController {

    private final AdminRepository adminRepository;
    private final SignService signService;

    @GetMapping(value = "/admin")
    public CollectionModel<SignResponse> getAll() throws Exception {
        List<SignResponse> admins = adminRepository.findAll().stream()
                .map(admin -> new SignResponse(admin))
                .collect(Collectors.toList());

        return CollectionModel.of(admins);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<SignResponse> signin(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<SignResponse> signup(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }

    @GetMapping("/admin/{account}")
    public ResponseEntity<SignResponse> getAdmin(@PathVariable String account) throws Exception {
        return new ResponseEntity<>(signService.getAdmin(account), HttpStatus.OK);
    }

    @PutMapping("/admin/{account}/allow")
    public ResponseEntity<SignResponse> allowAdmin(@PathVariable String account) throws Exception {
        return new ResponseEntity<>(signService.allowAdmin(account), HttpStatus.OK);
    }

}
