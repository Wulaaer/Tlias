package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Select("SELECT COUNT(*) FROM student WHERE clazz_id = #{clazzId}")
    Integer countByClazzId(Integer clazzId);

    List<Student> list(StudentQueryParam studentQueryParam);

    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time)" +
            " values (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime})")
    void save(Student student);

    @Select("select id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time" +
            " from student where id = #{id}")
    Student getById(Integer id);

    @Update("UPDATE student SET name = #{name}, no = #{no}, gender = #{gender}, phone = #{phone}, degree = #{degree}, id_card = #{idCard}, is_college = #{isCollege}, address = #{address}, graduation_date = #{graduationDate}, violation_count = #{violationCount}, violation_score = #{violationScore}, clazz_id = #{clazzId}, update_time = NOW()" +
            " WHERE id = #{id};\n")
    void updateById(Student student);

    void deleteByIds(List<Integer> ids);

    @Update("update student set violation_count = violation_count + 1, violation_score = violation_score + #{score} where id = #{id}")
    void updateVioMegById(Integer id, Integer score);

    @MapKey("degree")
    List<Map> countStuDegData();

    @MapKey("clazz")
    List<Map<String, Object>> countStuClazzData();
}
