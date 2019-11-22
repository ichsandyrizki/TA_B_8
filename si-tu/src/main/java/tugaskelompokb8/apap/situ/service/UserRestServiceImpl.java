package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.RoleDb;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.rest.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDb roleDb;

    @Autowired
    UserDb userDb;

    public UserRestServiceImpl(WebClient.Builder webClientBuild) {
        this.webClient = webClientBuild
                .baseUrl(Setting.Sivitas)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    @Override
    public UserModel getUserById(String id) {
        return userDb.findByIdUser(id);
    }

    static RestTemplate restTemplate = new RestTemplate();

    @Override
    public Mono<BaseResponse> getUserData(String idUser, long idRole) {
        if (idRole == 2) {
            String uri = "/api/employees/" + idUser;
            return this.webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(BaseResponse.class);

        } else if (idRole == 3 || idRole == 1) {
            String uri = "/api/teachers/" + idUser;
            return this.webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(BaseResponse.class);
        } else {
            String uri = "/api/students/" + idUser;
            return this.webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(BaseResponse.class);
        }
    }

        public String randomAngka () {
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                stringBuilder.append(String.valueOf(random.nextInt(10)));
            }
            return stringBuilder.toString();
        }

        public String randomHuruf () {
            String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder stringBuilder = new StringBuilder(2);
            for (int i = 0; i < 2; i++) {
                int index = (int) (alfabet.length() * Math.random());
                stringBuilder.append(alfabet.charAt(index));
            }
            return stringBuilder.toString();
        }

        public String generateNI (UserSivitasModel userSivitasModel, String role){
            DateFormat dateFormat = new SimpleDateFormat("ddMMYYYY");
            String angka = randomAngka();
            String huruf = randomHuruf();
            String id = userSivitasModel.getIdUSer();
            String tanggal = dateFormat.format(userSivitasModel.getTanggalLahir());
            return role + tanggal + huruf + angka + id;
        }

        @Override
        public Mono<BaseRest> registerUser (UserSivitasModel userSivitasModel){
            Map<String, String> data = new HashMap<String, String>();
            data.put("idUser", userSivitasModel.getIdUSer());
            data.put("nama", userSivitasModel.getNama());
            data.put("tempatLahir", userSivitasModel.getTempatLahir());
            data.put("tanggalLahir", userSivitasModel.getStringTanggalLahir());
            data.put("alamat", userSivitasModel.getAlamat());
            data.put("telepon", String.valueOf(userSivitasModel.getTelepon()));
            String uri = "";
            if (roleDb.findByIdRole(userSivitasModel.getIdRole()).getNama().equals("Admin TU")) {
                data.put("nip", generateNI(userSivitasModel, "P"));
                uri = "/api/employees";
            } else if (roleDb.findByIdRole(userSivitasModel.getIdRole()).getNama().equals("Siswa")) {
                data.put("nis", generateNI(userSivitasModel, "S"));
                uri = "/api/students";
            } else {
                data.put("nig", generateNI(userSivitasModel, "G"));
                uri = "/api/teachers";
            }

            return this.webClient
                    .post()
                    .uri(uri)
                    .syncBody(data)
                    .retrieve()
                    .bodyToMono(BaseRest.class);

        }
    }


