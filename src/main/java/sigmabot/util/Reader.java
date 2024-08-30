package sigmabot.util;

import sigmabot.ui.UiComponent;

public abstract class Reader {
    public abstract void readFile(String filePath, UiComponent ui);
}
