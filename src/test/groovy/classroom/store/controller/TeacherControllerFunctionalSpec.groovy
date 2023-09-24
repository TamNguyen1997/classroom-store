package classroom.store.controller

import classroom.store.model.command.teacher.Teacher
import classroom.store.model.command.teacher.TeacherCreateCommand
import classroom.store.model.command.teacher.TeacherUpdateCommand
import classroom.store.repository.TeacherRepository
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
    private TeacherRepository teacherRepository

    void "Create -> update -> fetch -> delete teacher"() {
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setBearerAuth(ADMIN_TOKEN)

        and:
        TeacherCreateCommand command = new TeacherCreateCommand(name: "test name", classroomId: UUID.fromString("7bf24b68-b2c9-4f1b-b2fc-16d9248870fa"))

        and:
        TeacherUpdateCommand updateCommand = new TeacherUpdateCommand(name: "Test name 2")

        when:
        ResponseEntity<Teacher> createResult =  testRestTemplate.exchange(
                "/teachers", HttpMethod.POST, new HttpEntity<TeacherCreateCommand>(command, headers), Teacher)

        UUID teacherId = createResult.body.id

        and:
        ResponseEntity<Teacher> updateResult =  testRestTemplate.exchange(
                "/teachers/${teacherId}", HttpMethod.PUT, new HttpEntity<TeacherUpdateCommand>(updateCommand, headers), Teacher)

        and:
        ResponseEntity<Teacher> fetchResult =  testRestTemplate.exchange(
                "/teachers/${teacherId}", HttpMethod.GET, new HttpEntity<TeacherCreateCommand>(headers), Teacher)

        and:
        ResponseEntity<Teacher> deleteResult =  testRestTemplate.exchange(
                "/teachers/${teacherId}", HttpMethod.DELETE, new HttpEntity<TeacherCreateCommand>(headers), Teacher)

        then:
        assert createResult.statusCode == HttpStatus.CREATED
        assert updateResult.statusCode == HttpStatus.CREATED
        assert fetchResult.statusCode == HttpStatus.OK
        assert deleteResult.statusCode == HttpStatus.NO_CONTENT
        assert updateResult.body.name == updateCommand.name
        assert teacherRepository.findById(createResult.body.id).isEmpty()
    }
}
