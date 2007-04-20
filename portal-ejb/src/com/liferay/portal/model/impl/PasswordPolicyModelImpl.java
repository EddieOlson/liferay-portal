/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.GetterUtil;
import com.liferay.util.XSSUtil;

import java.sql.Types;

import java.util.Date;

/**
 * <a href="PasswordPolicyModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PasswordPolicy</code> table in
 * the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.service.model.PasswordPolicy
 * @see com.liferay.portal.service.model.PasswordPolicyModel
 * @see com.liferay.portal.service.model.impl.PasswordPolicyImpl
 *
 */
public class PasswordPolicyModelImpl extends BaseModelImpl {
	public static String TABLE_NAME = "PasswordPolicy";
	public static Object[][] TABLE_COLUMNS = {
			{ "passwordPolicyId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.VARCHAR) },
			{ "userId", new Integer(Types.VARCHAR) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "name", new Integer(Types.VARCHAR) },
			{ "description", new Integer(Types.VARCHAR) },
			{ "changeable", new Integer(Types.BOOLEAN) },
			{ "changeRequired", new Integer(Types.BOOLEAN) },
			{ "minAge", new Integer(Types.INTEGER) },
			{ "storageScheme", new Integer(Types.VARCHAR) },
			{ "checkSyntax", new Integer(Types.BOOLEAN) },
			{ "allowDictionaryWords", new Integer(Types.BOOLEAN) },
			{ "minLength", new Integer(Types.INTEGER) },
			{ "history", new Integer(Types.BOOLEAN) },
			{ "historyCount", new Integer(Types.INTEGER) },
			{ "expireable", new Integer(Types.BOOLEAN) },
			{ "maxAge", new Integer(Types.INTEGER) },
			{ "warningTime", new Integer(Types.INTEGER) },
			{ "graceLimit", new Integer(Types.INTEGER) },
			{ "lockout", new Integer(Types.BOOLEAN) },
			{ "maxFailure", new Integer(Types.INTEGER) },
			{ "requireUnlock", new Integer(Types.BOOLEAN) },
			{ "lockoutDuration", new Integer(Types.INTEGER) },
			{ "resetFailureCount", new Integer(Types.INTEGER) }
		};
	public static boolean XSS_ALLOW_BY_MODEL = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.PasswordPolicy"), XSS_ALLOW);
	public static boolean XSS_ALLOW_COMPANYID = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.PasswordPolicy.companyId"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_USERID = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.PasswordPolicy.userId"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_USERNAME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.PasswordPolicy.userName"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_NAME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.PasswordPolicy.name"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_DESCRIPTION = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.PasswordPolicy.description"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_STORAGESCHEME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.PasswordPolicy.storageScheme"),
			XSS_ALLOW_BY_MODEL);
	public static long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.PasswordPolicyModel"));

	public PasswordPolicyModelImpl() {
	}

	public long getPrimaryKey() {
		return _passwordPolicyId;
	}

	public void setPrimaryKey(long pk) {
		setPasswordPolicyId(pk);
	}

	public long getPasswordPolicyId() {
		return _passwordPolicyId;
	}

	public void setPasswordPolicyId(long passwordPolicyId) {
		if (passwordPolicyId != _passwordPolicyId) {
			_passwordPolicyId = passwordPolicyId;
		}
	}

	public String getCompanyId() {
		return GetterUtil.getString(_companyId);
	}

	public void setCompanyId(String companyId) {
		if (((companyId == null) && (_companyId != null)) ||
				((companyId != null) && (_companyId == null)) ||
				((companyId != null) && (_companyId != null) &&
				!companyId.equals(_companyId))) {
			if (!XSS_ALLOW_COMPANYID) {
				companyId = XSSUtil.strip(companyId);
			}

			_companyId = companyId;
		}
	}

	public String getUserId() {
		return GetterUtil.getString(_userId);
	}

	public void setUserId(String userId) {
		if (((userId == null) && (_userId != null)) ||
				((userId != null) && (_userId == null)) ||
				((userId != null) && (_userId != null) &&
				!userId.equals(_userId))) {
			if (!XSS_ALLOW_USERID) {
				userId = XSSUtil.strip(userId);
			}

			_userId = userId;
		}
	}

	public String getUserName() {
		return GetterUtil.getString(_userName);
	}

	public void setUserName(String userName) {
		if (((userName == null) && (_userName != null)) ||
				((userName != null) && (_userName == null)) ||
				((userName != null) && (_userName != null) &&
				!userName.equals(_userName))) {
			if (!XSS_ALLOW_USERNAME) {
				userName = XSSUtil.strip(userName);
			}

			_userName = userName;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
		}
	}

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public void setName(String name) {
		if (((name == null) && (_name != null)) ||
				((name != null) && (_name == null)) ||
				((name != null) && (_name != null) && !name.equals(_name))) {
			if (!XSS_ALLOW_NAME) {
				name = XSSUtil.strip(name);
			}

			_name = name;
		}
	}

	public String getDescription() {
		return GetterUtil.getString(_description);
	}

	public void setDescription(String description) {
		if (((description == null) && (_description != null)) ||
				((description != null) && (_description == null)) ||
				((description != null) && (_description != null) &&
				!description.equals(_description))) {
			if (!XSS_ALLOW_DESCRIPTION) {
				description = XSSUtil.strip(description);
			}

			_description = description;
		}
	}

	public boolean getChangeable() {
		return _changeable;
	}

	public boolean isChangeable() {
		return _changeable;
	}

	public void setChangeable(boolean changeable) {
		if (changeable != _changeable) {
			_changeable = changeable;
		}
	}

	public boolean getChangeRequired() {
		return _changeRequired;
	}

	public boolean isChangeRequired() {
		return _changeRequired;
	}

	public void setChangeRequired(boolean changeRequired) {
		if (changeRequired != _changeRequired) {
			_changeRequired = changeRequired;
		}
	}

	public int getMinAge() {
		return _minAge;
	}

	public void setMinAge(int minAge) {
		if (minAge != _minAge) {
			_minAge = minAge;
		}
	}

	public String getStorageScheme() {
		return GetterUtil.getString(_storageScheme);
	}

	public void setStorageScheme(String storageScheme) {
		if (((storageScheme == null) && (_storageScheme != null)) ||
				((storageScheme != null) && (_storageScheme == null)) ||
				((storageScheme != null) && (_storageScheme != null) &&
				!storageScheme.equals(_storageScheme))) {
			if (!XSS_ALLOW_STORAGESCHEME) {
				storageScheme = XSSUtil.strip(storageScheme);
			}

			_storageScheme = storageScheme;
		}
	}

	public boolean getCheckSyntax() {
		return _checkSyntax;
	}

	public boolean isCheckSyntax() {
		return _checkSyntax;
	}

	public void setCheckSyntax(boolean checkSyntax) {
		if (checkSyntax != _checkSyntax) {
			_checkSyntax = checkSyntax;
		}
	}

	public boolean getAllowDictionaryWords() {
		return _allowDictionaryWords;
	}

	public boolean isAllowDictionaryWords() {
		return _allowDictionaryWords;
	}

	public void setAllowDictionaryWords(boolean allowDictionaryWords) {
		if (allowDictionaryWords != _allowDictionaryWords) {
			_allowDictionaryWords = allowDictionaryWords;
		}
	}

	public int getMinLength() {
		return _minLength;
	}

	public void setMinLength(int minLength) {
		if (minLength != _minLength) {
			_minLength = minLength;
		}
	}

	public boolean getHistory() {
		return _history;
	}

	public boolean isHistory() {
		return _history;
	}

	public void setHistory(boolean history) {
		if (history != _history) {
			_history = history;
		}
	}

	public int getHistoryCount() {
		return _historyCount;
	}

	public void setHistoryCount(int historyCount) {
		if (historyCount != _historyCount) {
			_historyCount = historyCount;
		}
	}

	public boolean getExpireable() {
		return _expireable;
	}

	public boolean isExpireable() {
		return _expireable;
	}

	public void setExpireable(boolean expireable) {
		if (expireable != _expireable) {
			_expireable = expireable;
		}
	}

	public int getMaxAge() {
		return _maxAge;
	}

	public void setMaxAge(int maxAge) {
		if (maxAge != _maxAge) {
			_maxAge = maxAge;
		}
	}

	public int getWarningTime() {
		return _warningTime;
	}

	public void setWarningTime(int warningTime) {
		if (warningTime != _warningTime) {
			_warningTime = warningTime;
		}
	}

	public int getGraceLimit() {
		return _graceLimit;
	}

	public void setGraceLimit(int graceLimit) {
		if (graceLimit != _graceLimit) {
			_graceLimit = graceLimit;
		}
	}

	public boolean getLockout() {
		return _lockout;
	}

	public boolean isLockout() {
		return _lockout;
	}

	public void setLockout(boolean lockout) {
		if (lockout != _lockout) {
			_lockout = lockout;
		}
	}

	public int getMaxFailure() {
		return _maxFailure;
	}

	public void setMaxFailure(int maxFailure) {
		if (maxFailure != _maxFailure) {
			_maxFailure = maxFailure;
		}
	}

	public boolean getRequireUnlock() {
		return _requireUnlock;
	}

	public boolean isRequireUnlock() {
		return _requireUnlock;
	}

	public void setRequireUnlock(boolean requireUnlock) {
		if (requireUnlock != _requireUnlock) {
			_requireUnlock = requireUnlock;
		}
	}

	public int getLockoutDuration() {
		return _lockoutDuration;
	}

	public void setLockoutDuration(int lockoutDuration) {
		if (lockoutDuration != _lockoutDuration) {
			_lockoutDuration = lockoutDuration;
		}
	}

	public int getResetFailureCount() {
		return _resetFailureCount;
	}

	public void setResetFailureCount(int resetFailureCount) {
		if (resetFailureCount != _resetFailureCount) {
			_resetFailureCount = resetFailureCount;
		}
	}

	public Object clone() {
		PasswordPolicyImpl clone = new PasswordPolicyImpl();
		clone.setPasswordPolicyId(getPasswordPolicyId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setChangeable(getChangeable());
		clone.setChangeRequired(getChangeRequired());
		clone.setMinAge(getMinAge());
		clone.setStorageScheme(getStorageScheme());
		clone.setCheckSyntax(getCheckSyntax());
		clone.setAllowDictionaryWords(getAllowDictionaryWords());
		clone.setMinLength(getMinLength());
		clone.setHistory(getHistory());
		clone.setHistoryCount(getHistoryCount());
		clone.setExpireable(getExpireable());
		clone.setMaxAge(getMaxAge());
		clone.setWarningTime(getWarningTime());
		clone.setGraceLimit(getGraceLimit());
		clone.setLockout(getLockout());
		clone.setMaxFailure(getMaxFailure());
		clone.setRequireUnlock(getRequireUnlock());
		clone.setLockoutDuration(getLockoutDuration());
		clone.setResetFailureCount(getResetFailureCount());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		PasswordPolicyImpl passwordPolicy = (PasswordPolicyImpl)obj;
		long pk = passwordPolicy.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		PasswordPolicyImpl passwordPolicy = null;

		try {
			passwordPolicy = (PasswordPolicyImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = passwordPolicy.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private long _passwordPolicyId;
	private String _companyId;
	private String _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private boolean _changeable;
	private boolean _changeRequired;
	private int _minAge;
	private String _storageScheme;
	private boolean _checkSyntax;
	private boolean _allowDictionaryWords;
	private int _minLength;
	private boolean _history;
	private int _historyCount;
	private boolean _expireable;
	private int _maxAge;
	private int _warningTime;
	private int _graceLimit;
	private boolean _lockout;
	private int _maxFailure;
	private boolean _requireUnlock;
	private int _lockoutDuration;
	private int _resetFailureCount;
}