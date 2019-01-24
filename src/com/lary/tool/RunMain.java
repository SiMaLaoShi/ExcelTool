package com.lary.tool;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;


public class RunMain {

    final static String Suffix = ".xlsx";

    public static void DoParser() {
        try {
            // 选中文件打开上次保存的路径
            File selectedFile = null;
            String lastPath = PropConf.get(PropConf.Key_LastPath);

            // 打开上次路径
            JFileChooser jfc = null;
            jfc = new JFileChooser(lastPath);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx");
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.setFileFilter(filter);

            int returnVal = jfc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());

                if (selectedFile.isFile()) {
                    PropConf.save(PropConf.Key_LastPath, selectedFile.getParent());

                    if (selectedFile.getName().toLowerCase().endsWith(Suffix)) {
                        parserByDif(selectedFile);
                    } else {
                        System.out.println(selectedFile.getName() + "²»ÊÇ.xlsxÎÄ¼þ");
                    }
                } else if (selectedFile.isDirectory()) {
                    PropConf.save(PropConf.Key_LastPath, selectedFile.getAbsolutePath());

                    ArrayList<File> list = new ArrayList<File>();
                    getFiles(list, selectedFile);
                    if (list.size() == 0) {
                        System.out.println("表格数据为空");
                        return;
                    }

                    if (list != null) {
                        for (File temp : list) {
                            Parser.parseExcel(temp);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void parserByDif(File file) throws IOException {
        String name = file.getName().replaceAll(Suffix, "");
        if (name.endsWith("_B")) {
            //ParserB.parseExcel(file);
        } else {
            Parser.parseExcel(file);
        }
    }

    public static void getFiles(final ArrayList<File> fileList, File file) {

        file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                if (pathname.isFile()) {
                    if (pathname.getName().toLowerCase().endsWith(Suffix)) {
                        fileList.add(pathname);
                    }
                } else if (pathname.isDirectory()) {
                    getFiles(fileList, pathname);
                }
                return false;
            }
        });
    }

    public static void main(String[] args) {
        // 工具入口
        DoParser();
    }
}