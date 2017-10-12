package ru.round.Utils;

import java.nio.file.Paths;
import java.util.prefs.Preferences;

/**
 * Created by GrishukovVM on 27.01.2016.
 */
public class PrefSettings{

    Preferences userPrefs = Preferences.userNodeForPackage(PrefSettings.class);
    static final String ROOTNODEPREF = "bankround_KTFL";
    public PrefSettings() {
        this.userPrefs = Preferences.userRoot().node(ROOTNODEPREF);
    }

    public String GetJDBCThin() {
        return  userPrefs.get("JDBCThin","jdbc:oracle:thin:USER/PASSWORD@IP:PORT:SID");
    }

    public String GetDateOn() {
        return  userPrefs.get("GetDateOn","31.08.2017");
    }

    public String GetOutputDir() {
        return  userPrefs.get("OutputDir", Paths.get("").toAbsolutePath().toString());
    }

}
