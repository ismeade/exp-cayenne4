package com.ade.exp.cayenne.base.create;

import com.ade.exp.cayenne.base.persistent.User;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by liyang on 18-4-3.
 */
public class CreateExp {

    private static ServerRuntime cayenneRuntime;

    static {
        cayenneRuntime = ServerRuntime
                .builder()
                .addConfig("cayenne-base.xml")
                .build();
    }

    private static void test1() {
        ObjectContext context = cayenneRuntime.newContext();
        User user = context.newObject(User.class);
        user.setName("user888");
        user.setAddress("user 888 address");
        context.commitChanges();
    }

    private static void test2() {
        ObjectContext context1 = cayenneRuntime.newContext();

        Integer result = cayenneRuntime.performInTransaction(() -> {
            // commit one or more contexts
            User user1 = Cayenne.objectForPK(context1, User.class, 200);
            user1.setAge(user1.getAge() + 10);
            context1.commitChanges();
            // after changing some objects in context1, commit again

            // return an arbitrary result or null if we don't care about the result
            return 5;
        });
    }

    public static void main(String[] args) {
        //test1();
        Executor executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(CreateExp::test2);
        }
    }

}
