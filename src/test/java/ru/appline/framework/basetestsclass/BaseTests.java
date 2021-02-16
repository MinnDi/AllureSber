package ru.appline.framework.basetestsclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.ManagerPages;

import static ru.appline.framework.utils.AllureListener.addScreenshot;

public class BaseTests {

    /**
     * Менеджер страничек
     * @see ManagerPages#getManagerPages()
     */
    protected ManagerPages app = ManagerPages.getManagerPages();

    @BeforeEach
    public void beforeEach() {
        InitManager.initFramework();
    }

    @AfterEach
    public void afterEach() {
        addScreenshot();
        InitManager.quitFramework();
    }
}
