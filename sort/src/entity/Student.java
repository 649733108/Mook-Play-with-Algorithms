package entity;
/*
 * Created by wxn
 * 2018/12/1 5:33
 */


public class Student implements Comparable<Student> {

	private String name;
	private int score;

	public Student(String name,int score){
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(Student anotherStudent) {

		if (this.score<anotherStudent.score)
			return 1;
		if (this.score>anotherStudent.score)
			return -1;
		return this.name.compareTo(anotherStudent.name);
	}

	@Override
	public String toString() {
		return "question.Student " + "name: " + name
				+ " score: " + score;
	}
}
