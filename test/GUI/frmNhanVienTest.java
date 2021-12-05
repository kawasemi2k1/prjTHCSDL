/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ADMIN
 */
public class frmNhanVienTest {
    
    public frmNhanVienTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadFile method, of class frmNhanVien.
     */
    @Test
    public void testLoadFile() {
        System.out.println("loadFile");
        frmNhanVien instance = new frmNhanVien();
        instance.loadFile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveFile method, of class frmNhanVien.
     */
    @Test
    public void testSaveFile() {
        System.out.println("saveFile");
        frmNhanVien instance = new frmNhanVien();
        instance.saveFile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
