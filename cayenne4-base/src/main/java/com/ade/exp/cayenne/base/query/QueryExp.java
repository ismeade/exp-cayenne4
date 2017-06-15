package com.ade.exp.cayenne.base.query;

import com.ade.exp.cayenne.base.persistent.Company;
import com.ade.exp.cayenne.base.persistent.User;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;

import java.util.List;

/**
 *
 * Created by liyang on 2017/6/15.
 */
public class QueryExp {

    private static ServerRuntime cayenneRuntime;

    static {
        cayenneRuntime = ServerRuntime
                .builder()
                .addConfig("cayenne-base.xml")
                .build();
    }

    private static void base1() {
        ObjectContext context = cayenneRuntime.newContext();
        List<User> userList = ObjectSelect
                .query(User.class)
//                .where(User.NAME.like("%%"))
//                .pageSize(2)
                .select(context);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    private static void base2() {
        ObjectContext context = cayenneRuntime.newContext();
        List<User> userList = ObjectSelect
                .query(User.class)
                .where(User.NAME.eq("user888"))
                .select(context);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    private static void base3() {
        ObjectContext context = cayenneRuntime.newContext();
        List<Company> companyList = ObjectSelect
                .query(Company.class)
                .where(Company.USERS.dot(User.NAME).eq("user888"))
                .prefetch(Company.USERS.disjoint())
                .select(context);
        for (Company company : companyList) {
            System.out.println(company);
            System.out.println(company.getUsers().get(0));
        }
        context.commitChanges();
    }

    private static void base4() {
        ObjectContext context = cayenneRuntime.newContext();
        long size = ObjectSelect
                .query(User.class)
                .where(User.NAME.eq("user888"))
                .selectCount(context);
        System.out.println(size);
    }

    private static void base5() {
        ObjectContext context = cayenneRuntime.newContext();
        User user = ObjectSelect
                .query(User.class)
                .where(User.NAME.eq("user888"))
                .selectOne(context);
        System.out.println(user);
    }

    private static void base6() {
        ObjectContext context = cayenneRuntime.newContext();
        List<String> nameList = ObjectSelect
                .query(User.class)
                .column(User.NAME)
                .where(User.NAME.eq("user888"))
                .select(context);
        for (String name : nameList) {
            System.out.println(name);
        }
    }

    private static void base7() {
        ObjectContext context = cayenneRuntime.newContext();
        List<Object[]> list = ObjectSelect
                .query(User.class)
                .columns(User.NAME, User.ADDRESS, User.COMPANY)
                .where(User.NAME.eq("user888"))
                .select(context);
        for (Object[] objs : list) {
            for (Object obj : objs) {
                System.out.println(obj);
            }
        }
    }

    public static void main(String[] args) {
        base1();
    }

}