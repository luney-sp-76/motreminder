package com.motbookingreminder.controller;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WebControllerTest {

    private final WebController webController = new WebController();

    @Test
    void showForm_returnsNumberPlateView() {
        assertThat(webController.showForm()).isEqualTo("numberPlate");
    }

    @Test
    void showLogin_returnsLoginView() {
        assertThat(webController.showLogin()).isEqualTo("login");
    }

    @Test
    void showReminderForm_returnsSetReminderView() {
        assertThat(webController.showReminderForm()).isEqualTo("setreminder");
    }

    @Test
    void setDate_returnsSetDateView() {
        assertThat(webController.setDate()).isEqualTo("setdate");
    }

    @Test
    void showAccount_returnsAccountView() {
        assertThat(webController.showAccount()).isEqualTo("account");
    }
}
