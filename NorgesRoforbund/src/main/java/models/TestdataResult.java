package models;

public class TestdataResult {
    private int id;
    private float test_result;
    private String test_time;
    private Test test_id;
    private Testdata testdata_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTest_result() {
        return test_result;
    }

    public void setTest_result(float test_result) {
        this.test_result = test_result;
    }

    public String getTest_time() {
        return test_time;
    }

    public void setTest_time(String test_time) {
        this.test_time = test_time;
    }

    public Test getTest_id() {
        return test_id;
    }

    public void setTest_id(Test test_id) {
        this.test_id = test_id;
    }

    public Testdata getTestdata_id() {
        return testdata_id;
    }

    public void setTestdata_id(Testdata testdata_id) {
        this.testdata_id = testdata_id;
    }
}
