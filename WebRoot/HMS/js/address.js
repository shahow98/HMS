$().ready(function() {
	//省
	var str_pro = "";
	for (var i = 0, len = province.length; i < len; i++) {
		var p = province[i].name;
		var str = "<option>" + p + "</option>";
		str_pro = str_pro + str;
	}
	$("#province").append(str_pro);
	//市
	$("[name='city']").focus(function() {
		if ($("[name='province']").val() == "") {
			$("#city").empty();
			$("#city").append("<option>没有找到相关数据</option>");
		} else {
			var pro_name = $("[name='province']").val();
			var pro_id = undefined;
			for (var i = 0, len = province.length; i < len; i++) {
				if (pro_name == province[i].name)
					pro_id = province[i].id;
			}
			if (pro_id != undefined) {
				var str_city = "";
				for (var i = 0, len = city[pro_id].length; i < len; i++) {
					if (len == 1) {
						var p = city[pro_id][i].province;
						var str = "<option>" + p + "</option>";
						str_city = str_city + str;
						break;
					}
					var p = city[pro_id][i].name;
					var str = "<option>" + p + "</option>";
					str_city = str_city + str;
				}
				$("#city").empty();
				$("#city").append(str_city);
			}
		}
	});
	//区
	$("[name='district']").focus(function() {
		if ($("[name='province']").val() == "" || $("[name='city']").val() == "") {
			$("#district").empty();
			$("#district").append("<option>没有找到相关数据</option>");
		} else {
			var pro_name = $("[name='province']").val();
			var pro_id = undefined;
			for (var i = 0, len = province.length; i < len; i++) {
				if (pro_name == province[i].name)
					pro_id = province[i].id;
			}
			var city_name = $("[name='city']").val();
			var city_id = undefined;
			for (var i = 0, len = city[pro_id].length; i < len; i++) {
				if (len == 1) {
					city_id = city[pro_id][i].id;
					break;
				}
				if (city_name == city[pro_id][i].name)
					city_id = city[pro_id][i].id;
			}
			var str_dis = "";
			for (var i = 0, len = area[city_id].length; i < len; i++) {
				var p = area[city_id][i].name;
				var str = "<option>" + p + "</option>";
				str_dis = str_dis + str;
			}
			$("#district").empty();
			$("#district").append(str_dis);
		}
	});
});
