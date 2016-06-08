package ee.ttu.idu0200.web.rest;

import ee.ttu.idu0200.web.dao.ClassroomDao;
import ee.ttu.idu0200.web.domain.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classroom/classroomservice")
public class ClassroomRestController {

  @Autowired
  private ClassroomDao classroomDao;

  @RequestMapping
  public Classroom findClassroom(@RequestParam long id) {
    return classroomDao.findOne(id);
  }
}
