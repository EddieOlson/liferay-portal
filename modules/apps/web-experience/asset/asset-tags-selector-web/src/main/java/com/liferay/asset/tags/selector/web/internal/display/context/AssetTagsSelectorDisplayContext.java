/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.asset.tags.selector.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagServiceUtil;
import com.liferay.asset.tags.selector.web.internal.search.EntriesChecker;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.asset.util.comparator.AssetTagNameComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class AssetTagsSelectorDisplayContext {

	public AssetTagsSelectorDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest request) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_request = request;
	}

	public String getClearResultsURL() {
		PortletURL clearResultsURL = _getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		_displayStyle = ParamUtil.getString(_request, "displayStyle", "list");

		return _displayStyle;
	}

	public String getEventName() {
		if (Validator.isNotNull(_eventName)) {
			return _eventName;
		}

		_eventName = ParamUtil.getString(
			_request, "eventName",
			_renderResponse.getNamespace() + "selectTag");

		return _eventName;
	}

	public List<DropdownItem> getFilterItemsDropdownItems() {
		return new DropdownItemList(_request) {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getFilterNavigationDropdownItems());
						dropdownGroupItem.setLabel("filter-by-navigation");
					});

				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getOrderByDropdownItems());
						dropdownGroupItem.setLabel("order-by");
					});
			}
		};
	}

	public List<NavigationItem> getNavigationItems() {
		List<NavigationItem> navigationItems = new ArrayList<>();

		NavigationItem entriesNavigationItem = new NavigationItem();

		entriesNavigationItem.setActive(true);

		PortletURL mainURL = _renderResponse.createRenderURL();

		entriesNavigationItem.setHref(mainURL.toString());

		entriesNavigationItem.setLabel(LanguageUtil.get(_request, "tags"));

		navigationItems.add(entriesNavigationItem);

		return navigationItems;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(_request, "orderByType", "asc");

		return _orderByType;
	}

	public String getSearchActionURL() {
		PortletURL searchActionURL = _getPortletURL();

		return searchActionURL.toString();
	}

	public String[] getSelectedTagNames() {
		if (ArrayUtil.isNotEmpty(_selectedTagNames)) {
			return _selectedTagNames;
		}

		_selectedTagNames = ParamUtil.getStringValues(
			_renderRequest, "selectedTagNames");

		return _selectedTagNames;
	}

	public String getSortingURL() {
		PortletURL sortingURL = _getPortletURL();

		sortingURL.setParameter(
			"orderByType",
			Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

		return sortingURL.toString();
	}

	public SearchContainer getTagsSearchContainer() {
		if (_tagsSearchContainer != null) {
			return _tagsSearchContainer;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		SearchContainer tagsSearchContainer = new SearchContainer(
			_renderRequest, _getPortletURL(), null, "there-are-no-tags");

		String keywords = _getKeywords();

		if (Validator.isNotNull(keywords)) {
			tagsSearchContainer.setSearch(true);
		}

		String orderByCol = _getOrderByCol();

		tagsSearchContainer.setOrderByCol(orderByCol);

		boolean orderByAsc = false;

		String orderByType = getOrderByType();

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		tagsSearchContainer.setOrderByComparator(
			new AssetTagNameComparator(orderByAsc));

		tagsSearchContainer.setOrderByType(orderByType);

		tagsSearchContainer.setRowChecker(
			new EntriesChecker(_renderRequest, _renderResponse));

		int tagsCount = AssetTagServiceUtil.getTagsCount(
			themeDisplay.getScopeGroupId(), keywords);

		tagsSearchContainer.setTotal(tagsCount);

		List<AssetTag> tags = AssetTagServiceUtil.getTags(
			_getGroupIds(), keywords, tagsSearchContainer.getStart(),
			tagsSearchContainer.getEnd(),
			tagsSearchContainer.getOrderByComparator());

		tagsSearchContainer.setResults(tags);

		_tagsSearchContainer = tagsSearchContainer;

		return _tagsSearchContainer;
	}

	public int getTotalItems() {
		SearchContainer tagsSearchContainer = getTagsSearchContainer();

		return tagsSearchContainer.getTotal();
	}

	public List<ViewTypeItem> getViewTypeItems() {
		PortletURL portletURL = _renderResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "changeDisplayStyle");
		portletURL.setParameter("redirect", PortalUtil.getCurrentURL(_request));

		return new ViewTypeItemList(_request, portletURL, getDisplayStyle()) {
			{
				addTableViewTypeItem();
			}
		};
	}

	public boolean isDisabledTagsManagementBar() throws PortalException {
		SearchContainer tagsSearchContainer = getTagsSearchContainer();

		if (tagsSearchContainer.getTotal() <= 0) {
			return true;
		}

		return false;
	}

	public boolean isShowTagsSearch() throws PortalException {
		if (Validator.isNotNull(_getKeywords())) {
			return true;
		}

		SearchContainer tagsSearchContainer = getTagsSearchContainer();

		if (tagsSearchContainer.getTotal() > 0) {
			return true;
		}

		return false;
	}

	private List<DropdownItem> _getFilterNavigationDropdownItems() {
		return new DropdownItemList(_request) {
			{
				add(
					dropdownItem -> {
						dropdownItem.setActive(true);
						dropdownItem.setHref(_getPortletURL());
						dropdownItem.setLabel("all");
					});
			}
		};
	}

	private long[] _getGroupIds() {
		if (ArrayUtil.isNotEmpty(_groupIds)) {
			return _groupIds;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_groupIds = StringUtil.split(
			ParamUtil.getString(_request, "groupIds"), 0L);

		if (ArrayUtil.isEmpty(_groupIds)) {
			_groupIds = new long[] {themeDisplay.getScopeGroupId()};
		}

		return _groupIds;
	}

	private String _getKeywords() {
		if (Validator.isNotNull(_keywords)) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_request, "keywords", null);

		return _keywords;
	}

	private String _getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(_request, "orderByCol", "name");

		return _orderByCol;
	}

	private List<DropdownItem> _getOrderByDropdownItems() {
		return new DropdownItemList(_request) {
			{
				add(
					dropdownItem -> {
						dropdownItem.setActive(true);
						dropdownItem.setHref(
							_getPortletURL(), "orderByCol", "name");
						dropdownItem.setLabel("name");
					});
			}
		};
	}

	private PortletURL _getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter("eventName", getEventName());
		portletURL.setParameter(
			"selectedTagNames", StringUtil.merge(getSelectedTagNames()));

		return portletURL;
	}

	private String _displayStyle;
	private String _eventName;
	private long[] _groupIds;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final HttpServletRequest _request;
	private String[] _selectedTagNames;
	private SearchContainer _tagsSearchContainer;

}