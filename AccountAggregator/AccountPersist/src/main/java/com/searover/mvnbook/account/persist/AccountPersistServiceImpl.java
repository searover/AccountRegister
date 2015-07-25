package com.searover.mvnbook.account.persist;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

/**
 * Created by searover on 7/22/15.
 */
public class AccountPersistServiceImpl implements AccountPersistService {

    private static final String ELEMENT_ROOT = "account-persist";
    private static final String ELEMENT_ACCOUNTS = "accounts";
    private static final String ELEMENT_ACCOUNT = "account";
    private static final String ELEMENT_ACCOUNT_ID = "id";
    private static final String ELEMENT_ACCOUNT_NAME = "name";
    private static final String ELEMENT_ACCOUNT_EMAIL = "email";
    private static final String ELEMENT_ACCOUNT_PASSWORD = "password";
    private static final String ELEMENT_ACCOUNT_ACTIVATED = "activated";

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    private String file;

    private SAXReader reader = new SAXReader();

    private Document readDocument() throws AccountPersistException {
        File dataFile = new File(file);
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            Document doc = DocumentFactory.getInstance().createDocument();
            Element rootEle = doc.addElement(ELEMENT_ROOT);
            rootEle.addElement(ELEMENT_ACCOUNTS);
            writeDocument(doc);
        }
        try {
            return reader.read(new File(file));
        } catch (DocumentException e) {
            throw new AccountPersistException("Unable to read persist data xml", e);
        }
    }

    private void writeDocument(Document doc) throws AccountPersistException {
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            // 用来创建一个带缩进和挑选的友好格式
            XMLWriter writer = new XMLWriter(out, OutputFormat.createPrettyPrint());
            writer.write(doc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new AccountPersistException("Unable to write persist data xml", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new AccountPersistException("Unable to close persist data xml writer", e);
            }
        }
    }

    public Account create(Account account) throws AccountPersistException {
        Document doc = readDocument();
        Element accountsEle = doc.getRootElement().element(ELEMENT_ACCOUNTS);
        accountsEle.add(buildAccountElement(account));
        writeDocument(doc);
        return account;
    }

    private Element buildAccountElement(Account account) {
        Element element = DocumentFactory.getInstance().createElement(ELEMENT_ACCOUNT);
        element.addElement(ELEMENT_ACCOUNT_ID).setText(account.getId());
        element.addElement(ELEMENT_ACCOUNT_NAME).setText(account.getName());
        element.addElement(ELEMENT_ACCOUNT_EMAIL).setText(account.getEmail());
        element.addElement(ELEMENT_ACCOUNT_PASSWORD).setText(account.getPassword());
        element.addElement(ELEMENT_ACCOUNT_ACTIVATED).setText(account.isActivated() ? "true" : "false");

        return element;
    }

    public Account read(String id) throws AccountPersistException {
        Document doc = readDocument();
        Element accountsEle = doc.getRootElement().element(ELEMENT_ACCOUNTS);
        for (Element accountEle : (List<Element>) accountsEle.elements()) {
            if (accountEle.elementText(ELEMENT_ACCOUNT_ID).equals(id)){
                return buildAccount(accountEle);
            }
        }
        return null;
    }

    private Account buildAccount(Element element) {
        Account account = new Account();
        account.setId(element.elementText(ELEMENT_ACCOUNT_ID));
        account.setName(element.elementText(ELEMENT_ACCOUNT_NAME));
        account.setEmail(element.elementText(ELEMENT_ACCOUNT_EMAIL));
        account.setPassword(element.elementText(ELEMENT_ACCOUNT_PASSWORD));
        account.setActivated("true".equals(element.elementText(ELEMENT_ACCOUNT_ACTIVATED)) ? true : false);
        return account;
    }

    public Account update(Account account) throws AccountPersistException {
        return null;
    }

    public void delete(String id) throws AccountPersistException {
    }
}
