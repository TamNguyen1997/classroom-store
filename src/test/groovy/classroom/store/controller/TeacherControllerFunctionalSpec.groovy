package classroom.store.controller


import classroom.store.model.command.teacher.TeacherCreateCommand
import classroom.store.orm.TeacherDb
import classroom.store.service.TeacherPersistentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static classroom.store.TestValues.ADMIN_TOKEN

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeacherControllerFunctionalSpec extends Specification {

    @Autowired
    private TestRestTemplate testRestTemplate

    @Autowired
    private TeacherPersistentService teacherPersistentService

    void "Create teacher"() {
        given:
        TeacherCreateCommand command = new TeacherCreateCommand(name: "test name", classroomId: UUID.fromString("7bf24b68-b2c9-4f1b-b2fc-16d9248870fa"))
        HttpHeaders headers = new HttpHeaders()
        headers.setBearerAuth(ADMIN_TOKEN)
        when:
        ResponseEntity<TeacherDb> result =  testRestTemplate.exchange(
                "/teachers", HttpMethod.POST, new HttpEntity<TeacherCreateCommand>(command, headers), TeacherDb.class)

        then:
        assert result.statusCode == HttpStatus.CREATED
        assert teacherPersistentService.fetch(result.body.id)
    }
}
