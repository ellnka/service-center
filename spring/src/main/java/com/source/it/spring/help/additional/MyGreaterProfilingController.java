package com.source.it.spring.help.additional;

public class MyGreaterProfilingController implements MyGreaterProfilingControllerMBean {
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
