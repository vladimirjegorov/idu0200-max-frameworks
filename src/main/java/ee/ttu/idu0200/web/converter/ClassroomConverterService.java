package ee.ttu.idu0200.web.converter;


import ee.ttu.idu0200.web.domain.Classroom;
import ee.ttu.idu0200.web.form.ClassroomForm;
import org.springframework.stereotype.Service;

//import static org.apache.commons.lang3.time.DateUtils.parseDate;

@Service
public class ClassroomConverterService {

  public ClassroomForm toClassroomForm(Classroom classroom) {
    ClassroomForm classroomForm = new ClassroomForm();
    classroomForm.setId(classroom.getId());
    classroomForm.setTitle(classroom.getTitle());
    classroomForm.setSeatingCapacity(classroom.getSeatingCapacity());
    classroomForm.setDescription(classroom.getDescription());
    return classroomForm;
  }

  public Classroom toClassroom(ClassroomForm classroomForm) {
    Classroom classroom = new Classroom();
    classroom.setId(classroomForm.getId());
    classroom.setTitle(classroomForm.getTitle());
    classroom.setSeatingCapacity(classroomForm.getSeatingCapacity());
    classroom.setDescription(classroomForm.getDescription());

    return classroom;
  }
}
