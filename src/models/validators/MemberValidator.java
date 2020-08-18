package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Member;
import utils.DBUtil;

public class MemberValidator {
	public static List<String> validate(Member m, Boolean email_duplicate_check_flag, Boolean password_check_flag){
		List<String> errors = new ArrayList<String>();

		String email_error = _validateEmail(m.getEmail(), email_duplicate_check_flag);
		if(!email_error.equals("")){
			errors.add(email_error);
		}

		String name_error = _validateName(m.getName());
		if(!name_error.equals("")){
			errors.add(name_error);
		}

		String password_error = _validatePassword(m.getPassword(), password_check_flag);
		if(!password_error.equals("")){
			errors.add(password_error);
		}

		return errors;
	}

	private static String _validateEmail(String email, Boolean email_duplicate_check_flag){
		if( email == null || email.equals("")){
			return "メールアドレスを入力してください。";
		}

		if(email_duplicate_check_flag){
			EntityManager em = DBUtil.createEntityManager();
			long members_count = (long)em.createNamedQuery("checkRegisteredEmail", Long.class)
					                     .setParameter("email", email)
					                     .getSingleResult();
			em.close();
			if(members_count > 0){
				return "入力されたメールアドレスはすでに存在しています。";
			}
		}

		return "";
	}

	private static String _validateName(String name){
		if(name == null || name.equals("")){
			return "氏名を入力してください";
		}

		return "";
	}

	private static String _validatePassword(String password, Boolean password_check_flag){
		if(password_check_flag && (password ==null || password.equals(""))){
			return "パスワードを入力してください";
		}

		return "";
	}
}
