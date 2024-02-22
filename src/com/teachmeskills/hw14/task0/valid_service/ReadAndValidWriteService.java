package com.teachmeskills.hw14.task0.valid_service;

import com.teachmeskills.hw14.task0.util.Consts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * Class with static method to reading lines from a file and outputting to the console
 * to checking document numbers for validity
 * to writing document numbers in report files and logs.
 */
public class ReadAndValidWriteService {
    private ReadAndValidWriteService() {

    }

    public static void readAndWriteService() {
        System.out.println("Enter path: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            System.out.println("Lines from the file: ");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                FileWriter fwError = new FileWriter(Consts.PATH_TO_ERROR_LOG, true);

                if (line.length() == Consts.DOC_CONTRACT_LENGTH && line.startsWith("docnum")) {

                    FileWriter fwInvalid = new FileWriter(Consts.PATH_TO_INVALID_REPORT_FILE, true);

                    String str = "";
                    for (char ch : line.toCharArray()) {
                        if (((int) ch >= 48 && (int) ch <= 57) || ((int) ch >= 65 && (int) ch <= 90) || ((int) ch >= 97 && (int) ch <= 122)) {
                            str = str + ch;
                        } else {
                            try {
                                fwInvalid.write("\n" + line + " Invalid because it contains spaces and characters other than letters and numbers");
                            } catch (IOException e) {
                                fwError.write("\n" + line + " Unsuccessful");
                            } catch (Exception e) {
                                fwError.write("\n" + line + " Fail");
                            } finally {
                                fwInvalid.close();
                            }
                            return;
                        }
                    }
                    try (FileWriter fwExecution = new FileWriter(Consts.PATH_TO_EXECUTION_LOG, true); FileWriter fwNewLine = new FileWriter(Consts.PATH_TO_DOC, true)) {
                        fwExecution.write("\n" + str + " Successfully");

                        Files.write(Paths.get(Consts.PATH_TO_DOC), str.getBytes(), StandardOpenOption.APPEND);
                        fwNewLine.write("\n");
                    } catch (IOException e) {
                        fwError.write("\n" + str + " Unsuccessful");
                    } catch (Exception e) {
                        fwError.write("\n" + str + " Fail");
                    }

                } else if (line.length() == Consts.DOC_CONTRACT_LENGTH && line.startsWith("contract")) {

                    FileWriter fwInvalid = new FileWriter(Consts.PATH_TO_INVALID_REPORT_FILE, true);

                    String str = "";
                    for (char ch : line.toCharArray()) {
                        if (((int) ch >= 48 && (int) ch <= 57) || ((int) ch >= 65 && (int) ch <= 90) || ((int) ch >= 97 && (int) ch <= 122)) {
                            str = str + ch;
                        } else {
                            try {
                                fwInvalid.write("\n" + line + " Invalid because it contains spaces and characters other than letters and numbers");
                            } catch (IOException e) {
                                fwError.write("\n" + line + " Unsuccessful");
                            } catch (Exception e) {
                                fwError.write("\n" + line + " Fail");
                            } finally {
                                fwInvalid.close();
                            }
                            return;
                        }
                    }
                    try (FileWriter fwExecution = new FileWriter(Consts.PATH_TO_EXECUTION_LOG, true); FileWriter fwNewLine = new FileWriter(Consts.PATH_TO_CONTRACT, true)) {
                        fwExecution.write("\n" + str + " Successfully");

                        Files.write(Paths.get(Consts.PATH_TO_CONTRACT), str.getBytes(), StandardOpenOption.APPEND);
                        fwNewLine.write("\n");
                    } catch (IOException e) {
                        fwError.write("\n" + str + " Unsuccessful");
                    } catch (Exception e) {
                        fwError.write("\n" + str + " Fail");
                    }
                } else {
                    try (FileWriter fwInvalid = new FileWriter(Consts.PATH_TO_INVALID_REPORT_FILE, true)) {
                        fwInvalid.write("\n" + line + " Invalid because the characters are longer or shorter than 15 or do not start with the sequence docnum or contract");
                    } catch (IOException e) {
                        fwError.write("\n" + line + " Unsuccessful");
                    } catch (Exception e) {
                        fwError.write("\n" + line + " Fail");
                    } finally {
                        fwError.close();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Exception message");
        } catch (Exception e) {
            System.out.println("Another exception message");
        } finally {
            scanner.close();
        }
    }
}
