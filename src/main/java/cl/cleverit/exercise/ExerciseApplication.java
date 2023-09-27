package cl.cleverit.exercise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}


	/**
	 * Script for creation of dummy admins, in order to test the JWT integration
	 *
	 */
	@Bean
	CommandLineRunner run(IAdminService adminService, IRoleService roleService){
		return args -> {
			log.info("generando admins dummy");
			String roleAdmin = "ROLE_ADMIN";
			String roleSuperAdmin = "ROLE_SUPERADMIN";

			roleService.saveRole(RoleEntity.builder().rolename(roleAdmin).build());
			roleService.saveRole(RoleEntity.builder().rolename(roleSuperAdmin).build());

			adminService.saveAdmin(AdminEntity.builder().username("pparker").password("spider").build());
			adminService.saveAdmin(AdminEntity.builder().username("jjameson").password("boss").build());

			adminService.addRoleToAdmin("pparker", roleAdmin);
			adminService.addRoleToAdmin("jjameson", roleSuperAdmin);
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
