package com.example.game;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages({"com.example.game.services", "com.example.game.controllers"})
@Suite
public class TestSuite {
}
