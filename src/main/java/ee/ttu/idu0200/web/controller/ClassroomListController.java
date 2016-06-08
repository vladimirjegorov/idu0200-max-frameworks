package ee.ttu.idu0200.web.controller;

import ee.ttu.idu0200.web.dao.ClassroomDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classroom/s")
public class ClassroomListController {

  private Logger LOG = LogManager.getLogger(ClassroomController.class);


  @Autowired
  private ClassroomDao classroomDao;

  @RequestMapping
  public String findAll(Model model) {
    model.addAttribute("classroomList", classroomDao.findAll());

    LOG.info("Opening the list of classrooms");

    return "classroom/classroomList";
  }
}
